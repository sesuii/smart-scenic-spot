package com.smartscenicspot.db.neo4j.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/26 12:27
 **/

@RelationshipProperties
@Data
public class WalkingRelationship {

    @RelationshipId
    private Long id;

    @TargetNode
    private AttractionNode attractionNode;

    private Double cost;

    private Double distance;

    private Integer capacity;

    private Integer current;

    private Double normalCost;

}
