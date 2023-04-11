package com.smartscenicspot.dto;

import com.smartscenicspot.vo.RatingScoreVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String phone;
    private Date birthday;
    private String gender;
    private Byte status;
    private Set<RatingScoreVo> ratingScores;
    private Set<InterestTagDto> interestTags = new HashSet<>();
    private List<RecommendationDto> recommendationList;
}