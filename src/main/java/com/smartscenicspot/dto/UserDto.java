package com.smartscenicspot.dto;

import com.smartscenicspot.vo.RatingScoreVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.smartscenicspot.db.pgql.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    private Date gmtModified;
    private Long id;
    private String name;
    private Date birthday;
    private String gender;
    private Byte status;
    private List<RatingScoreVo> ratingScores;
    private List<InterestTagDto> interestTags;
    private List<RecommendationDto> recommendationList;
}