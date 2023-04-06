package com.smartscenicspot.db.neo4j.entity;

import lombok.Data;
import org.neo4j.driver.types.Point;
import org.springframework.data.neo4j.core.convert.ConvertWith;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.persistence.GeneratedValue;
import java.util.HashSet;
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

    @ConvertWith(converterRef = "ByteArray with length 1")
    private Byte status;

    private Point location;

    private Long parentId;

    @Relationship(type = "WALKING")
    private Set<WalkingRelationship> walkingRoutes = new HashSet<>();

    @Relationship(type = "SHORTEST_PATH_TO")
    private Set<ShortestPathsRelationship> shortestPaths = new HashSet<>();

}
