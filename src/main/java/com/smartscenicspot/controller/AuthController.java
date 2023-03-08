package com.smartscenicspot.controller;

import com.smartscenicspot.domain.resp.Result;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.vo.AdminVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "/adminlogin")
    public Result<?> adminLogin(@RequestBody AdminVo adminVo) {
        Map<String, String> token = adminService.toLogin(adminVo);
        return Result.success(token);
    }

    @PostMapping(value = "/wechatlogin")
    public Result<?> weChatLogin(@RequestBody String code) {

        return null;
    }

}
