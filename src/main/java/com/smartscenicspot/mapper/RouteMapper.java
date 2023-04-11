package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.Route;
import com.smartscenicspot.dto.StaticRouteDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    Route toEntity(StaticRouteDto staticRouteDto);

    StaticRouteDto toDto(Route route);
    List<StaticRouteDto> toDtoList(List<Route> routes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Route partialUpdate(StaticRouteDto staticRouteDto, @MappingTarget Route route);

}