package com.smartscenicspot.db.neo4j.repository;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * Neo4j景点节点
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/29 20:34
 **/
@Repository
public interface Neo4jAttractionRepository extends Neo4jRepository<AttractionNode, Long> {
//    @Query("MATCH (n:Attraction) RETURN n ORDER BY n.attractionId ASC return n")
//    Result findAllWithoutRelationships();
}
