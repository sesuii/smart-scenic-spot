package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.Recommendation;
import com.smartscenicspot.dto.RecommendationDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecommendationMapper {

    RecommendationMapper INSTANCE = Mappers.getMapper(RecommendationMapper.class);

    @Mapping(source = "attractionName", target = "attraction.name")
    @Mapping(source = "attractionId", target = "attraction.id")
    Recommendation toEntity(RecommendationDto recommendationDto);

    @InheritInverseConfiguration(name = "toEntity")
    RecommendationDto toDto(Recommendation recommendation);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recommendation partialUpdate(RecommendationDto recommendationDto, @MappingTarget Recommendation recommendation);

    List<RecommendationDto> toDtoList(List<Recommendation> recommendations);
}