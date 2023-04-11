package com.smartscenicspot.db.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.GeneratedValue;
import java.util.Set;

/**
 * 存放在 Neo4j 中的景区节点
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/26 12:20
 **/

@Node("Attraction")
@Data
public class AttractionNode {
    @Id
    @GeneratedValue
    private Long id;

    private Long attractionId;

    private String name;

    private Integer capacity;

    private String category;

    private Integer current;

    private Integer status;

    private Double latitude;

    private Double longitude;

    private Long parentId;

    @Relationship(type = "WALKING")
    private Set<WalkingRelationship> walkingRoutes;

//    @Relationship(type = "SHORTEST_PATH_TO")
//    private Set<ShortestPathsRelationship> shortestPaths;

}
