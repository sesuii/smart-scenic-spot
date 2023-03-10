package com.smartscenicspot.controller;

import com.smartscenicspot.repository.UserRepository;
import com.smartscenicspot.vo.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息管理控制类
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/23 22:59
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping("/userinfo")
    public Result<?> getUserInfo() {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.printf(openid);

        return Result.success();
    }

}
