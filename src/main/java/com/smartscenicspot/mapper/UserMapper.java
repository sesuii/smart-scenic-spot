package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.User;
import com.smartscenicspot.dto.UserDto;
import com.smartscenicspot.vo.UserVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserVo userVo);

    UserVo toVo(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserVo userVo, @MappingTarget User user);

    User DtoToEntity(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdateByDto(UserDto userDto, @MappingTarget User user);

    @AfterMapping
    default void linkRatingScores(@MappingTarget User user) {
        user.getRatingScores().forEach(ratingScore -> ratingScore.setUser(user));
    }

    @AfterMapping
    default void linkRecommendationList(@MappingTarget User user) {
        user.getRecommendationList().forEach(recommendationList -> recommendationList.setUser(user));
    }

}