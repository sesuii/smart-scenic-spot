package com.smartscenicspot.mapper;

import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.db.pgql.pojo.RatingScore;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RatingScoreMapper {

    RatingScoreMapper INSTANCE = Mappers.getMapper(RatingScoreMapper.class);

    RatingScore toEntity(RatingScoreDto ratingScoreDto);

    RatingScoreDto toDto(RatingScore ratingScore);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RatingScore partialUpdate(RatingScoreDto ratingScoreDto, @MappingTarget RatingScore ratingScore);

    List<RatingScoreDto> toDtos(List<RatingScore> content);
}