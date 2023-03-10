package com.smartscenicspot.controller;

import com.smartscenicspot.dto.AdminDto;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 景区管理员控制类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:54
 **/

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping("/info")
    public Result<?> getAdminInfo() {
        AdminDto adminDto = adminService.getAdminInfo();
        return Result.success(adminDto);
    }

}
