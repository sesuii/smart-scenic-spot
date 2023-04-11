package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.entity.InterestTag;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link InterestTag} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class InterestTagDto implements Serializable {
    private Long id;
    private String tagName;
}