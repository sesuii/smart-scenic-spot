package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.entity.User;
import com.smartscenicspot.dto.UserDto;
import com.smartscenicspot.vo.UserVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserVo userVo);

    UserVo toVo(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserVo userVo, @MappingTarget User user);

    User DtoToEntity(UserDto userDto);

    UserDto toDto(User user);

    User partialUpdateByDto(UserDto userDto, @MappingTarget User user);

    @AfterMapping
    default void mapperNestAttributes(User user, @MappingTarget UserDto userDto) {
        userDto.setRatingScores(RatingScoreMapper.INSTANCE.toVoList(user.getRatingScores()));
        userDto.setRecommendationList(RecommendationMapper.INSTANCE.toDtoList(user.getRecommendationList()));
    }

    List<UserDto> toDtoList(List<User> users);
}