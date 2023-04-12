package com.smartscenicspot.controller;

import com.smartscenicspot.service.RatingScoreService;
import com.smartscenicspot.service.TourGroupService;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.vo.DailyHotVo;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 统计分析模块
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 3:58
 **/
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Resource
    UserService userService;

    @Resource
    TourGroupService tourGroupService;

    @Resource
    RatingScoreService ratingScoreService;

    @GetMapping("/user-info")
    public Result<?> analyseUserInfo(@RequestParam Integer pageSize,
                                     @RequestParam Integer currentPage) {
        PageVo<?> pageVo = userService.analyseUserInfo(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }

    @GetMapping("/group-count")
    public Result<?> countTourGroup() {
        int groupCount = tourGroupService.countActiveGroups();
        return Result.success(groupCount);
    }

    @GetMapping("/analyse-hot")
    public Result<?> analyseHot(@RequestParam String startTime, @RequestParam String endTime) {
        DailyHotVo dailyHotVo = ratingScoreService.getHotDataByTimeSlot(startTime, endTime);
        return Result.success(dailyHotVo);
    }
}
