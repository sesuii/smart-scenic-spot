package com.smartscenicspot.mapper;

import com.smartscenicspot.dto.TourGroupDto;
import com.smartscenicspot.db.pgql.entity.TourGroup;
import com.smartscenicspot.vo.TourGroupVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface TourGroupMapper {

    TourGroupMapper INSTANCE = Mappers.getMapper(TourGroupMapper.class);
    TourGroup voToEntity(TourGroupVo tourGroupVo);

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    TourGroup dtoToEntity(TourGroupDto tourGroupDto);

    TourGroupDto toDto(TourGroup tourGroup);

    TourGroupVo toVo(TourGroup tourGroup);
    List<TourGroupDto> toDtoList(List<TourGroup> content);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TourGroup partialUpdate(TourGroupDto tourGroupDto, @MappingTarget TourGroup tourGroup);

}