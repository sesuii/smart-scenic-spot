package com.smartscenicspot.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信用户登录认证过滤器
 *
 * @author <a href="mailto: sjiahui@gmail.com">songjiahui</a>
 * @since 2023/3/6 20:46
 **/
public class WeChatAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Resource
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public WeChatAuthenticationFilter() {
        super(new AntPathRequestMatcher("/auth/login","POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String code = request.getParameter("code");
        // TODO 用 code 换取 openid 和 session_key 进行验证

        // TODO WeChatAuthenticationFilter 要先于 AuthenticationFilter 对于微信用户登录用 code 换取 openid...

        WeChatAuthenticationToken token = null;

        return this.getAuthenticationManager().authenticate(token);
    }

    public void setDetails(HttpServletRequest request, WeChatAuthenticationToken token) {
        token.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
