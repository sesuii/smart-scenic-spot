package com.smartscenicspot.dto;

import com.smartscenicspot.db.pgql.pojo.RatingScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link RatingScore} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RatingScoreDto implements Serializable {
    private Date gmtModified;
    private Long id;
    private Double score;
    private String comment;
    private Long userId;
    private Long attractionId;
    private int currentPage = 1;
    private int pageSize = 10;
}