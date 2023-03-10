package com.smartscenicspot.filter;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.utils.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截验证带 JWT 的请求，身份验证过滤器
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/6 23:02
 **/
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 在过滤链最前端拦截携带 JWT 的请求，并且进行验证。
     * 对于未携带 Token 的请求则放行到后续过滤器处理。
     * 1. 根据 token 中解析的凭证
     * 2. 从 Redis 中确认凭证是否有效
     * @param request 请求
     * @param response 响应
     * @param filterChain 过滤链
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String securityHeader = request.getHeader(SecurityConstant.SECURITY_HEADER);
        if(securityHeader == null ||
                !securityHeader.startsWith(SecurityConstant.SECURITY_HEADER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = securityHeader.substring(SecurityConstant.SECURITY_HEADER_PREFIX.length());
        String account = JwtUtil.parseJWT(token).getSubject();
        String storedToken = (String)redisTemplate.opsForValue()
                .get(RedisConstant.USER_PREFIX + account);
        if(!token.equals(storedToken)) {
            try {
                throw new Exception("用户身份过期，请重新登录");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(account, null, null)
        );
        filterChain.doFilter(request, response);
    }
}
