package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.RatingScore;
import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.vo.RatingScoreVo;
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

    @Mapping(source = "attractionName", target = "attraction.name")
    @Mapping(source = "attractionId", target = "attraction.id")
    @Mapping(source = "userName", target = "user.name")
    @Mapping(source = "userId", target = "user.id")
    RatingScore voToEntity(RatingScoreVo ratingScoreVo);

    @InheritInverseConfiguration(name = "voToEntity")
    RatingScoreVo toVo(RatingScore ratingScore);

    @InheritConfiguration(name = "voToEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RatingScore partialUpdateByVo(RatingScoreVo ratingScoreVo, @MappingTarget RatingScore ratingScore);

}