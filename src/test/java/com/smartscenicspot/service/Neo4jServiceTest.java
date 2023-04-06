package com.smartscenicspot.service;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/5 0:13
 **/
@SpringBootTest
public class Neo4jServiceTest {

    @Resource
    Neo4jService neo4jService;

    @Test
    void test() {
        AttractionNode attractionNode = neo4jService.demo();
        assert attractionNode != null;
    }
}
