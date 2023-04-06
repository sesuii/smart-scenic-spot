package com.smartscenicspot.mapper;

import com.smartscenicspot.dto.InterestTagDto;
import com.smartscenicspot.db.pgql.pojo.InterestTag;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InterestTagMapper {

    InterestTagMapper INSTANCE = Mappers.getMapper(InterestTagMapper.class);
    InterestTag toEntity(InterestTagDto interestTagDto);

    Set<InterestTag> toEntityList(Set<InterestTagDto> interestTagDtoSet);

    InterestTagDto toVo(InterestTag interestTag);
    List<InterestTagDto> toVoList(List<InterestTag> tags);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InterestTag partialUpdate(InterestTagDto interestTagDto, @MappingTarget InterestTag interestTag);

}