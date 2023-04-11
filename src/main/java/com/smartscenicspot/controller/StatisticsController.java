package com.smartscenicspot.controller;

import com.smartscenicspot.service.UserService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/9 3:58
 **/
@RestController
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Resource
    UserService userService;

    @GetMapping("/user-info")
    public Result<?> analyseUserInfo(@RequestParam Integer pageSize,
                                     @RequestParam Integer currentPage) {
        PageVo<?> pageVo = userService.analyseUserInfo(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }
}
