package com.smartscenicspot.controller;

import com.smartscenicspot.service.StaffService;
import com.smartscenicspot.vo.PageVo;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 景区工作人员管理
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:58
 **/

@RestController
@RequestMapping("/admin/staff")
public class StaffController {

    @Resource
    StaffService staffService;
    @GetMapping("/all")
    public Result<?> getDtos(@RequestParam Integer pageSize, @RequestParam Integer currentPage) {
        PageVo<?> pageVo = staffService.getAllDtos(currentPage - 1, pageSize);
        return Result.success(pageVo);
    }
}
