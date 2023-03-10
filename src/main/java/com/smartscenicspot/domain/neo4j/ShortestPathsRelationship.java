package com.smartscenicspot.domain.neo4j;

import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/26 12:28
 **/
@RelationshipProperties
@EqualsAndHashCode
public class ShortestPathsRelationship {

    @RelationshipId
    private Long id;

    @TargetNode
    private AttractionNode attractionNode;

    private List<List<Long>> shortestPaths;

}