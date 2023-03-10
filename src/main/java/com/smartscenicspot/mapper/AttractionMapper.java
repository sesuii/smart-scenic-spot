package com.smartscenicspot.mapper;

import com.smartscenicspot.domain.Attraction;
import com.smartscenicspot.dto.AttractionDto;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.vo.AttractionVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/31 11:01
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface AttractionMapper {

    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    AttractionDto toDto(Attraction attraction);

    List<AttractionDto> toDtoList(List<Attraction> attractions);

    AttractionVo toVo(Attraction attraction);
    Attraction voToEntity(AttractionVo attractionVo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attraction partialUpdate(AttractionUpdateDto attractionUpdateDto, @MappingTarget Attraction attraction);
}
