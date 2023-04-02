package com.smartscenicspot.service.Impl;

import com.smartscenicspot.neo4j.entity.AttractionNode;
import com.smartscenicspot.neo4j.repo.Neo4jAttractionRepository;
import com.smartscenicspot.service.Neo4jService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:10
 **/

@Service
public class Neo4jServiceImpl implements Neo4jService {
    @Resource
    Neo4jAttractionRepository neo4jAttractionRepository;


    @Override
    public AttractionNode demo() {
        List<AttractionNode> attractionNodes = neo4jAttractionRepository.findAll();
        System.out.println(attractionNodes.size());
        return attractionNodes.get(1);
    }
}
