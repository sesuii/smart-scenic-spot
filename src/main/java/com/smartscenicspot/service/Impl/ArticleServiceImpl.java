package com.smartscenicspot.service.Impl;

import com.smartscenicspot.mapper.ArticleMapper;
import com.smartscenicspot.pojo.Article;
import com.smartscenicspot.repository.ArticleRepository;
import com.smartscenicspot.service.ArticleService;
import com.smartscenicspot.vo.ArticleVo;
import com.smartscenicspot.vo.PageVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 10:56
 **/

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleRepository articleRepository;

    @Override
    public ArticleVo createNewArticle(ArticleVo articleVo) {
        Article article = ArticleMapper.INSTANCE.toEntity(articleVo);
        Article saved = articleRepository.save(article);
        return ArticleMapper.INSTANCE.toVo(saved);
    }

    @Override
    public ArticleVo getVoById(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if(article == null) {
            return null;
        }
        return ArticleMapper.INSTANCE.toVo(article);
    }

    @Override
    public PageVo<?> getAllVos(int page, int size) {
        Page<Article> articles = articleRepository.findAll(PageRequest.of(page, size));
        return PageVo.builder()
                .data(Collections.singletonList(ArticleMapper.INSTANCE
                        .toVoList(articles.getContent())))
                .totalPages(articles.getTotalPages())
                .totalElements(articles.getTotalElements())
                .build();
    }


}
