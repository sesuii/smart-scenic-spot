package com.smartscenicspot.config;

import com.smartscenicspot.handler.WebSocketCustomHandler;
import com.smartscenicspot.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * Websocket 配置
 *
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 19:11
 **/

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    WebSocketCustomHandler webSocketCustomHandler;

    @Resource
    WebSocketInterceptor webSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(@NotNull WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketCustomHandler, "/contact/{id}")
                .addInterceptors(webSocketInterceptor)
                .setAllowedOrigins("*")
                .setAllowedOriginPatterns("*");
    }
}
