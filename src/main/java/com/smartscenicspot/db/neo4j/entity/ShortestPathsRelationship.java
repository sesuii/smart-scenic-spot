package com.smartscenicspot.db.neo4j.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

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

    private List<String> shortestPaths;

    private List<Double> costs;

}