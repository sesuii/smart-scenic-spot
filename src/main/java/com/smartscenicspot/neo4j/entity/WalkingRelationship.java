package com.smartscenicspot.neo4j.entity;

import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/26 12:27
 **/

@RelationshipProperties
@EqualsAndHashCode
public class WalkingRelationship {

    @RelationshipId
    private Long id;

    @TargetNode
    private AttractionNode attractionNode;

    private Integer capacity;

    private Integer current;

}
