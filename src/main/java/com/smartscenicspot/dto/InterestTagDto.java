package com.smartscenicspot.dto;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.pojo.InterestTag} entity
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