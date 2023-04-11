package com.smartscenicspot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.RatingScore} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingScoreVo implements Serializable {
    private Double score;
    private String comment;
    private Long userId;
    private String userName;
    private Long attractionId;
    @NotBlank
    private String attractionName;
}