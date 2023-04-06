package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.pojo.Staff;
import com.smartscenicspot.dto.StaffDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffMapper {

    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    Staff toEntity(StaffDto staffDto);

    StaffDto toDto(Staff staff);
    List<StaffDto> toDtoList(List<Staff> staffs);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffDto staffDto, @MappingTarget Staff staff);

}