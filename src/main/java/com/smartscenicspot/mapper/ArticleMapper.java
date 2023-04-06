package com.smartscenicspot.mapper;

import com.smartscenicspot.db.pgql.pojo.Article;
import com.smartscenicspot.vo.ArticleVo;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article toEntity(ArticleVo articleVo);

    @Mapping(source = "gmtCreate", target = "publishTime")
    ArticleVo toVo(Article article);

    List<ArticleVo> toVoList(List<Article> content);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Article partialUpdate(ArticleVo articleVo, @MappingTarget Article article);

}