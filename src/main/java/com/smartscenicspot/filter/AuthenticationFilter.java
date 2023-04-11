package com.smartscenicspot.filter;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.constant.SecurityConstant;
import com.smartscenicspot.utils.JwtUtil;
import com.smartscenicspot.wrapper.HeaderMapRequestWrapper;
import io.jsonwebtoken.JwtException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

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
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String securityHeader = request.getHeader(SecurityConstant.SECURITY_HEADER);
        String secWebsocketHeader = request.getHeader(SecurityConstant.WEBSOCKET_AUTH);
        String token;
        if(secWebsocketHeader != null) {
            HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
            requestWrapper.addHeader(SecurityConstant.SECURITY_HEADER, SecurityConstant.SECURITY_HEADER_PREFIX + secWebsocketHeader);
            response.addHeader(SecurityConstant.WEBSOCKET_AUTH, secWebsocketHeader);
            request = requestWrapper;
            token = secWebsocketHeader;
        }
        else {
            if(securityHeader == null ||
                    !securityHeader.startsWith(SecurityConstant.SECURITY_HEADER_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            token = securityHeader.substring(SecurityConstant.SECURITY_HEADER_PREFIX.length());
        }

        String account;
            try {
                account = JwtUtil.parseJWT(token).getSubject();
            } catch (JwtException e) {
                filterChain.doFilter(request, response);
                return;
            }
        String redisToken = (String)redisTemplate.opsForValue()
                .get(RedisConstant.USER_PREFIX + account);
        boolean isAdmin = redisToken.startsWith(SecurityConstant.ADMIN_LABEL);
        String storedToken = isAdmin ? redisToken.substring(SecurityConstant.ADMIN_LABEL.length())
                : redisToken;
        if(!token.equals(storedToken)) {
            try {
                throw new Exception("用户身份过期，请重新登录");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collection<GrantedAuthority> authorityCollection = null;
        if(isAdmin) {
            authorityCollection = Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(account, null, authorityCollection)
        );
        filterChain.doFilter(request, response);
    }
}
