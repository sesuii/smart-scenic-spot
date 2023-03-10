package com.smartscenicspot.repository;

import com.smartscenicspot.domain.neo4j.AttractionNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * Neo4j景点节点
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/29 20:34
 **/
public interface Neo4jAttractionRepository extends Neo4jRepository<AttractionNode, Long> {

}
