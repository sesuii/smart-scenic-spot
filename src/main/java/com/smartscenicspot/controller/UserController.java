package com.smartscenicspot.controller;

import com.smartscenicspot.constant.ResultEnum;
import com.smartscenicspot.dto.InterestTagDto;
import com.smartscenicspot.service.UserService;
import com.smartscenicspot.vo.Result;
import com.smartscenicspot.vo.UserVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    UserService userService;

    @GetMapping("/userinfo")
    public Result<?> getUserInfo() {
        String openid = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVo userVo = userService.getUserVo(openid);
        if(userVo == null) {
            return Result.success(ResultEnum.NOT_FOUND);
        }
        return Result.success(userVo);
    }

    @PutMapping("/update")
    public Result<?> userInfoUpdate(@RequestBody UserVo userVo) {
        UserVo updated = userService.updateUserInfo(userVo);
        return Result.success(updated);
    }

    @GetMapping("/allTag")
    public Result<?> getAllTags() {
        List<InterestTagDto> tagList = userService.getAllTags();
        return Result.success(tagList);
    }

}
