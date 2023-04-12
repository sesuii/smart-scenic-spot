package com.smartscenicspot.db.neo4j.entity;

import lombok.Data;
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
@Data
public class ShortestPathsRelationship {

    @RelationshipId
    private Long id;

    public ShortestPathsRelationship(List<String> viaPaths) {
        this.viaPaths = viaPaths;
    }

    @TargetNode
    private AttractionNode attractionNode;

    private List<String> viaPaths;

    private List<Double> costs;

    private List<Long> bestPath;

}