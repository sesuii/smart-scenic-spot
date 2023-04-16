package com.smartscenicspot.mapper;

import com.smartscenicspot.dto.AttractionQueryDto;
import com.smartscenicspot.vo.AttractionRouteVo;
import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.db.pgql.entity.Attraction;
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

    AttractionVo toVo(Attraction attraction);

    List<AttractionVo> toVoList(List<Attraction> attractions);

    Attraction voToEntity(AttractionVo attractionVo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attraction partialUpdate(AttractionUpdateDto attractionUpdateDto, @MappingTarget Attraction attraction);

    Attraction queryDtoToEntity(AttractionQueryDto attractionQueryDto);

    Attraction toEntity(AttractionRouteVo attractionRouteVo);

    AttractionRouteVo toRouteVo(Attraction attraction);

    List<AttractionRouteVo> toRouteVoList(List<Attraction> byIdIn);
}
