package com.smartscenicspot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.Recommendation} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecommendationDto implements Serializable {
    private Long id;
    private Double propensity;
    private Byte status;
    private Long userId;
    private String userName;
    private Long attractionId;
    @NotBlank
    private String attractionName;
}