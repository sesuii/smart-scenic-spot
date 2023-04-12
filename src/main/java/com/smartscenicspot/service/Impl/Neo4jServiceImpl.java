package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import com.smartscenicspot.db.neo4j.entity.ShortestPathsRelationship;
import com.smartscenicspot.db.neo4j.repository.Neo4jAttractionRepository;
import com.smartscenicspot.service.Neo4jService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:10
 **/

@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Resource
    Neo4jAttractionRepository neo4jAttractionRepository;



    @Override
    @Transactional(value = "neo4jTransactionManager")
    public List<?> getDynamicInfo() {
        return neo4jAttractionRepository.findNodes();
    }

    @Override
    public List<Long> getSingleSourcePath(Long sourceId, Long targetId) {
        ShortestPathsRelationship paths = neo4jAttractionRepository.shortestPaths(sourceId, targetId);
        String firstPath = paths.getViaPaths().get(0);
        return Arrays.stream(firstPath.split(" "))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public boolean changeStatus(Long attractionId) {
        AttractionNode attractionNode = new AttractionNode(attractionId);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                        .withMatcher("attractionId", ExampleMatcher.GenericPropertyMatchers.exact());
        AttractionNode node = neo4jAttractionRepository.findOne(Example.of(attractionNode, exampleMatcher)).orElse(null);
        node.setStatus(node.getStatus() == 1 ? 0 : 1);
        neo4jAttractionRepository.save(node);
        return true;
    }
}
