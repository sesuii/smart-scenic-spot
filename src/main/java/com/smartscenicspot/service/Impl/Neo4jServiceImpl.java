package com.smartscenicspot.service.Impl;

import com.smartscenicspot.db.neo4j.repository.Neo4jAttractionRepository;
import com.smartscenicspot.service.Neo4jService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(value = "neo4jTransactionManager")
    public List<?> getDynamicInfo() {
//        Result attractionNode = neo4jAttractionRepository.findAllWithoutRelationships();
//        return attractionNode.list();
        return null;
    }

}
