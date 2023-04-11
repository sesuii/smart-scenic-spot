package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.InterestTag;
import com.smartscenicspot.dto.InterestTagDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InterestTagMapper {

    InterestTagMapper INSTANCE = Mappers.getMapper(InterestTagMapper.class);
    InterestTag toEntity(InterestTagDto interestTagDto);

    InterestTagDto toDto(InterestTag interestTag);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InterestTag partialUpdate(InterestTagDto interestTagDto, @MappingTarget InterestTag interestTag);

    List<InterestTagDto> toDtoList(List<InterestTag> tags);
}