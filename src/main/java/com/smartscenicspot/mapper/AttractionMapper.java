package com.smartscenicspot.mapper;

import com.smartscenicspot.dto.AttractionUpdateDto;
import com.smartscenicspot.pojo.Attraction;
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
    public abstract Attraction partialUpdate(AttractionUpdateDto attractionUpdateDto, @MappingTarget Attraction attraction);

    // FIXME @AfterMapping 不起作用，根据文档换成抽象类后无法映射

}
