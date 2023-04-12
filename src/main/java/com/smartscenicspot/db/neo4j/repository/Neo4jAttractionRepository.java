package com.smartscenicspot.db.neo4j.repository;

import com.smartscenicspot.db.neo4j.entity.AttractionNode;
import com.smartscenicspot.db.neo4j.entity.ShortestPathsRelationship;
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

    @Query("MATCH (n1:Attraction)-[p:SHORTEST_PATH]-(n2:Attraction) " +
            "WHERE n1.attractionId = $source AND n2.attractionId = $target RETURN p")
    ShortestPathsRelationship shortestPaths(@Param("source") Long sourceId,
                                            @Param("target") Long targetId);
    @Modifying
    @Transactional
    @Query("MATCH (n:Attraction) WHERE n.attractionId = $id SET n.current = $current")
    void updateCurrentByAttractionId(@Param("id") Long attractionId, @Param("current") Long current);

    @Modifying
    @Transactional
    @Query("MATCH (n:Attraction) WHERE n.attractionId = $attractionId set n.status = 1 - $status")
    void updateAttractionStatus(@Param("attractionId") Long attractionId, @Param("status") Integer status);
}
