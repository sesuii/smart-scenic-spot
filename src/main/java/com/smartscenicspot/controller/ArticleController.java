package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.service.ArticleService;
import com.smartscenicspot.vo.ArticleVo;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * /api/article
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 11:40
 **/

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    ArticleService articleService;

    @GetMapping("/all")
    public Result<?> getArticles(@RequestParam Integer pageSize,
                                 @RequestParam Integer currentPage) {
        PageVo<?> pageVo = articleService.getAllVos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @GetMapping("/{id}")
    public Result<?> getArticleById(@PathVariable Long id) {
        ArticleVo articleVo = articleService.getVoById(id);
        if(articleVo == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(articleVo);
    }

    @PostMapping("/new")
    public Result<?> createNewArticle(@RequestBody ArticleVo articleVo) {
        ArticleVo newArticleVo = articleService.createNewArticle(articleVo);
        return Result.success(newArticleVo);
    }
}
