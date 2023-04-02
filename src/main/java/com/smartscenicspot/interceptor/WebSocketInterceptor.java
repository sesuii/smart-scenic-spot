package com.smartscenicspot.interceptor;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.repository.UserRepository;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import java.util.Map;

/**
 * websocket 拦截器
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/3 22:29
 **/
@Component
public class WebSocketInterceptor implements HandshakeInterceptor {

    @Resource
    UserRepository userRepository;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
         String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         attributes.put(RedisConstant.USER_OPENID, account);
         return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception exception) {

    }
}
