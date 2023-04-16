package com.smartscenicspot.db.neo4j.repository;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Neo4j景点节点
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/29 20:34
 **/
@Repository
public interface Neo4jAttractionRepository extends Neo4jRepository<AttractionNode, Long> {
    @Query("MATCH (n:Attraction) return n")
    List<AttractionNode> findNodes();

    @Modifying
    @Transactional
    @Query("MATCH (n:Attraction) WHERE n.attractionId = $id SET n.current = $current")
    void updateCurrentByAttractionId(@Param("id") Long id, @Param("current") Long current);

    @Query("MATCH (n:Attraction) where n.current >= n.capacity * 0.9 return n")
    List<AttractionNode> findOverCapacityNodes();

    @Modifying
    @Transactional
    @Query("MATCH (n:Attraction) WHERE n.attractionId = $attractionId set n.status = 1 - $status")
    void updateAttractionStatus(@Param("attractionId") Long attractionId, @Param("status") Integer status);


    @Modifying
    @Query("CALL gds.graph.drop($name) yield graphName return true")
    boolean dropGraphProjection(@Param("name") String name);

    @Modifying
    @Query("CALL gds.graph.create($name, 'Attraction', {WALKING: {properties: ['cost','distance','current','capacity']}}) yield graphName return true")
    boolean createGraphProjection(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("MATCH ()-[r:WALKING]->(n:Attraction) WHERE n.attractionId = $attractionId\n" +
            "SET r.cost = \n" +
            "  CASE \n" +
            "    WHEN n.current >= n.capacity THEN r.normalCost * 100\n" +
            "    WHEN n.current / toFloat(n.capacity) >= 0.85 \n" +
            "    THEN r.normalCost * (1 / (1 - n.current / toFloat(n.capacity)))\n" +
            "    ELSE r.normalCost \n" +
            "  END")
    void updateCost(@Param("attractionId") Long attractionId);

    @Modifying
    @Transactional
    @Query("match (n1:Attraction) \n" +
            "with collect(n1) as n1s\n" +
            "unwind n1s as n1\n" +
            "with [n in n1s] as n2s, n1\n" +
            "unwind n2s as n2\n" +
            "CALL gds.shortestPath.yens.stream($projectionName, {\n" +
            "    sourceNode: ID(n1),\n" +
            "    targetNode: ID(n2),\n" +
            "    k: 3,\n" +
            "    relationshipWeightProperty: 'cost'\n" +
            "})\n" +
            "YIELD index, sourceNode, targetNode, totalCost, nodeIds\n" +
            "WITH index, sourceNode, targetNode, totalCost, nodeIds,\n" +
            "reduce(s = \"\", x IN nodeIds | s + case when s <> '' then ' ' else '' end + gds.util.asNode(x).attractionId) as viaPath\n" +
            "WHERE totalCost > 0.0 and sourceNode <> targetNode\n" +
            "MATCH (a1: Attraction)-[p:SHORTEST_PATH]->(a2:Attraction)\n" +
            "WHERE ID(a1) = sourceNode and ID(a2) = targetNode\n" +
            "SET p.costs = REDUCE(s = [], i IN RANGE(0, SIZE(p.costs) - 1) |\n" +
            "  s + CASE i WHEN index THEN totalCost ELSE p.costs[i] END\n" +
            "), p.viaPaths = REDUCE(s = [], i IN RANGE(0, SIZE(p.viaPaths) - 1) |\n" +
            "  s + CASE i WHEN index THEN viaPath ELSE p.viaPaths[i] END\n" +
            ")\n" +
            "WITH p, index, nodeIds\n" +
            "where index = 0 SET p.bestPath = nodeIds")
    void updateShortestPath(@Param("projectionName") String projectionName);
}
