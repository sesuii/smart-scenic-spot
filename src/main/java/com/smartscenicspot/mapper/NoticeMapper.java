package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.Notice;
import com.smartscenicspot.dto.NoticeDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiahui
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoticeMapper {

    NoticeMapper INSTANCE = Mappers.getMapper(NoticeMapper.class);

    Notice toEntity(NoticeDto noticeDto);

    NoticeDto toDto(Notice notice);

    List<NoticeDto> toDtoList(List<Notice> notices);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Notice partialUpdate(NoticeDto noticeDto, @MappingTarget Notice notice);

}