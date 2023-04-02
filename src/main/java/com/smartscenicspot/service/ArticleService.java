package com.smartscenicspot.service;

import com.smartscenicspot.vo.ArticleVo;
import com.smartscenicspot.vo.PageVo;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 10:55
 **/
public interface ArticleService {

    ArticleVo createNewArticle(ArticleVo articleVo);

    ArticleVo getVoById(Long id);

    PageVo<?> getAllVos(int page, int size);
}
