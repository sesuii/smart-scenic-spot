package com.smartscenicspot.auth;

import com.smartscenicspot.domain.User;
import com.smartscenicspot.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.annotation.Resource;

/**
 * 微信用户登录认证逻辑实现
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:44
 **/
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    @Resource
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        WeChatAuthenticationToken token = (WeChatAuthenticationToken) authentication;
        // TODO 如果数据库没有该微信用户则创建新的用户，如果有则读取数据
        System.out.println("openid" + token.getPrincipal());
        System.out.println("session_key" + token.getCredentials());
        User user = userService.getByOpenid((String)token.getPrincipal());
        if(user == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        return new WeChatAuthenticationToken((String) token.getPrincipal(), (String) token.getCredentials(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
