package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.domain.Admin;
import com.smartscenicspot.domain.resp.Result;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 认证授权管理
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/2/27 22:01
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    AdminService adminService;

    @Resource
    UserService userService;

    @PostMapping(value = "/adminregister")
    public Result<?> adminRegister(@RequestBody AdminVo adminVo) {
        Admin admin = adminService.createAccount(adminVo);
        return Result.success(admin);
    }

    @PostMapping(value = "/adminlogin")
    public Result<?> adminLogin(@RequestBody AdminVo adminVo) {
        Map<String, String> token = adminService.toAdminLogin(adminVo);
        if(token == null) {
            return Result.failed(ResultEnum.AUTHORITY_FAILED);
        }
        return Result.success(token);
    }

    @PostMapping(value = "/wechatlogin")
    public Result<?> weChatLogin(@RequestBody String code) {
        Map<String, String> token = userService.toWeChatLogin(code);
        if(token == null) {
            return Result.failed(ResultEnum.AUTHORITY_FAILED);
        }
        return Result.success(token);
    }

}
