package com.smartscenicspot.auth;

import com.smartscenicspot.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 微信用户登录认证逻辑实现
 *
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:44
 **/
public class WeChatAuthenticationProvider implements AuthenticationProvider {

    UserService userService;

    public WeChatAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        WeChatAuthenticationToken token = (WeChatAuthenticationToken) authentication;
        // TODO
        System.out.println(token.getPrincipal());
        UserDetails user = userService.getByOpenid(String.valueOf(token.getPrincipal()));
        if(user == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        return new WeChatAuthenticationToken(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
