package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.RatingScoreDto;
import com.smartscenicspot.service.RatingScoreService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * /rating
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/6 16:26
 **/
@RestController
@RequestMapping("/rating")
public class RatingScoreController {

    @Resource
    RatingScoreService ratingScoreService;

    @PostMapping("new")
    public Result<?> addNewScore(@RequestBody RatingScoreDto ratingScoreDto) {
        boolean saved = ratingScoreService.addNewRatingScore(ratingScoreDto);
        return Result.success(saved);
    }

    @GetMapping("/{id}")
    public Result<?> getRatingScoreById(@PathVariable(name = "id") Long id) {
        RatingScoreDto ratingScoreDto = ratingScoreService.getDtoById(id);
        if(ratingScoreDto == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(ratingScoreDto);
    }

    @PostMapping("/all")
    public Result<?> getRatingScores(@RequestBody RatingScoreDto ratingScoreDto){
        PageVo<?> pageVo = ratingScoreService.getAllDtos(ratingScoreDto);
        return Result.success(pageVo);
    }

    @DeleteMapping("/{id}")
    public Result<?> delRatingScore(@PathVariable Long id) {
        boolean deleted = ratingScoreService.deleteRatingScore(id);
        return Result.success(deleted);
    }
}
