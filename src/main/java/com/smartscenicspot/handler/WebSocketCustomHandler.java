package com.smartscenicspot.handler;

import com.smartscenicspot.service.WebSocketService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/2 20:05
 **/
public class WebSocketCustomHandler implements WebSocketHandler {

    @Resource
    WebSocketService webSocketService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // FIXME test connection
        System.out.println("成功建立 WebSocket 连接！");
        webSocketService.handleOpen(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        webSocketService.handleMessage(session, message);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        webSocketService.handleError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // TODO 根据触发关闭的 status，处理连接
        webSocketService.handleClose(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
