package com.smartscenicspot.controller;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.service.AdminService;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.vo.AdminVo;
import com.smartscenicspot.vo.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "/adminlogin")
    public Result<?> adminLogin(@RequestBody AdminVo adminVo) {
        Map<String, String> token = adminService.toAdminLogin(adminVo);
        if(token == null) {
            return Result.failed(ResultEnum.AUTHORITY_FAILED);
        }
        return Result.success(token);
    }

    @PostMapping(value = "/wechatlogin")
    public Result<?> weChatLogin(@RequestBody Map<String, String> code) {
        Map<String, String> token = userService.toWeChatLogin(code.get("code"));
        if(token == null) {
            return Result.failed(ResultEnum.AUTHORITY_FAILED);
        }
        return Result.success(token);
    }


    @GetMapping(value = "/logout")
    public Result<?> logout() {
        String account = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        redisTemplate.opsForValue().getAndDelete(RedisConstant.USER_PREFIX + account);
        return Result.success("LOGOUT SUCCESS");
    }
}
