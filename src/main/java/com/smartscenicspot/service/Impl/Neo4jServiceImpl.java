package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import com.smartscenicspot.db.neo4j.entity.ShortestPathsRelationship;
import com.smartscenicspot.db.neo4j.repository.Neo4jAttractionRepository;
import com.smartscenicspot.db.pgql.repository.AttractionRepository;
import com.smartscenicspot.mapper.AttractionMapper;
import com.smartscenicspot.service.Neo4jService;
import com.smartscenicspot.vo.BestRouteResultVo;
import com.smartscenicspot.vo.RouteQueryVo;
import org.neo4j.driver.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:10
 **/

@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Resource
    Neo4jAttractionRepository neo4jAttractionRepository;

    @Resource
    AttractionRepository attractionRepository;

    @Resource
    Neo4jClient neo4jClient;

    @Override
    @Transactional(value = "neo4jTransactionManager")
    public List<?> getDynamicInfo() {
        return neo4jAttractionRepository.findNodes();
    }

    @Override
    public BestRouteResultVo getSingleSourcePath(RouteQueryVo routeQueryVo) {
        Long sourceId = attractionRepository
                .findNearestAttraction(routeQueryVo.getLatitude(), routeQueryVo.getLongitude()).getId();
        if(sourceId.equals(routeQueryVo.getTarget())) {
            return BestRouteResultVo.builder()
                    .viaNodeIds(List.of(sourceId))
                    .attractionRouteVos(AttractionMapper.INSTANCE
                            .toRouteVoList(attractionRepository.findByIdIn(List.of(sourceId))))
                    .build();
        }
        ShortestPathsRelationship paths = neo4jClient.query(
                "MATCH (n1:Attraction)-[p:SHORTEST_PATH]->(n2:Attraction) " +
                    "WHERE n1.attractionId = $source AND n2.attractionId = $target " +
                    "RETURN p.viaPaths as viaPaths, p.costs as totalCost"
                )
                .bind(sourceId).to("source")
                .bind(routeQueryVo.getTarget()).to("target")
                .fetchAs(ShortestPathsRelationship.class)
                .mappedBy((typeSystem, record) -> new ShortestPathsRelationship(
                    record.get("viaPaths").asList(Value::asString),
                        record.get("totalCost").asList(Value::asDouble))).one().orElse(null);
        if(paths == null) {
            return null;
        }
        List<Long> result =  Arrays.stream(paths.getViaPaths().get(0).split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        if(!result.get(0).equals(sourceId)) {
            Collections.reverse(result);
        }
        return BestRouteResultVo.builder()
                .viaNodeIds(result)
                .totalCost(paths.getCosts().get(0))
                .attractionRouteVos(AttractionMapper.INSTANCE
                        .toRouteVoList(attractionRepository.findByIdIn(result)))
                .build();
    }

    @Override
    public boolean changeStatus(Long attractionId, Integer status) {
        neo4jAttractionRepository.updateAttractionStatus(attractionId, status);
        return true;
    }

    @Override
    @Transactional(value = "neo4jTransactionManager", rollbackFor = Exception.class)
    public boolean imitateCrowdChange(Map<String, Long> changes) {
        String projectionName = "modified";
        Long attractionId = changes.get("attractionId");
        Long people = changes.get("people");
        neo4jAttractionRepository
                .updateCurrentByAttractionId(attractionId, people);
        neo4jAttractionRepository.updateCost(attractionId);
        neo4jAttractionRepository.createGraphProjection(projectionName);
        neo4jAttractionRepository.updateShortestPath(projectionName);
        neo4jAttractionRepository.dropGraphProjection(projectionName);
        return true;
    }

    @Override
    public BestRouteResultVo getMultipleBestPath(List<Long> attractionIds) {
        BestRouteResultVo realtimeHamiltonian = neo4jClient
                .query(
                        "WITH $nodeIdList as selection\n" +
                                "MATCH (a:Attraction) where a.attractionId in selection\n" +
                                "WITH collect(a) as attractions\n" +
                                "UNWIND attractions as a1\n" +
                                "WITH a1,\n" +
                                "     [a in attractions where a.attractionId > a1.attractionId] as a2s,\n" +
                                "     (size(attractions) - 1) as level,\n" +
                                "     attractions\n" +
                                "UNWIND a2s as a2\n" +
                                "CALL apoc.path.expandConfig(a1, {\n" +
                                "    relationshipFilter: 'SHORTEST_PATH>',\n" +
                                "    minLevel: level,\n" +
                                "    maxLevel: level,\n" +
                                "    whitelistNodes: attractions,\n" +
                                "    terminatorNode: [a2],\n" +
                                "    uniqueness: 'NODE_PATH'\n" +
                                "}) YIELD path\n" +
                                "WITH nodes(path) as orderedAttractions,\n" +
                                "    [n in nodes(path) | id(n)] as ids,\n" +
                                "    reduce (cost = 0, x in relationships(path) | cost + x.costs[0]) as totalCost,\n" +
                                "    [r in relationships(path) | r.bestPath] as shortestRouteNodeIds\n" +
                                "    order by totalCost LIMIT 1\n" +
                                "UNWIND range(0, size(orderedAttractions) - 1) as index\n" +
                                "UNWIND shortestRouteNodeIds[index] as shortestViaNodeId\n" +
                                "WITH orderedAttractions, totalCost, index,\n" +
                                "    CASE WHEN shortestRouteNodeIds[index][0] = ids[index]\n" +
                                "    THEN tail(collect(shortestViaNodeId))\n" +
                                "        ELSE tail(reverse(collect(shortestViaNodeId)))\n" +
                                "        END as orderedViaNodeIds\n" +
                                "  ORDER BY index\n" +
                                "UNWIND orderedViaNodeIds as orderedViaNodeId\n" +
                                "MATCH (c:Attraction) where id(c) = orderedViaNodeId\n" +
                                "RETURN [orderedAttractions[0].attractionId] + collect(c.attractionId) as viaNodeIds, totalCost"
                )
                .bind(attractionIds).to("nodeIdList")
                .fetchAs(BestRouteResultVo.class)
                .mappedBy((typeSystem, record) -> new BestRouteResultVo(
                        record.get("viaNodeIds").asList(Value::asLong),
                        record.get("totalCost").asDouble()))
                .one().orElse(null);
        realtimeHamiltonian.setAttractionRouteVos(AttractionMapper.INSTANCE
                .toRouteVoList(attractionRepository.findByIdIn(realtimeHamiltonian.getViaNodeIds())));
        return realtimeHamiltonian;
    }

    @Override
    public List<AttractionNode> getOverCapacity() {
        return neo4jAttractionRepository.findOverCapacityNodes();
    }
}
