package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.RatingScore;
import com.smartscenicspot.db.pgql.entity.Recommendation;
import com.smartscenicspot.dto.RecommendationDto;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecommendationMapper {
    Recommendation toEntity(RecommendationDto recommendationDto);

    RecommendationDto toDto(Recommendation recommendation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recommendation partialUpdate(RecommendationDto recommendationDto, @MappingTarget Recommendation recommendation);

    default Set<Double> ratingScoresToRatingScoreScores(Set<RatingScore> ratingScores) {
        return ratingScores.stream().map(RatingScore::getScore).collect(Collectors.toSet());
    }
}