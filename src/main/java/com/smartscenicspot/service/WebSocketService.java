package com.smartscenicspot.service;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/3 19:10
 **/
public interface WebSocketService {

    void handleOpen(WebSocketSession session);

    void handleClose(WebSocketSession session);

    void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException;


    /**
     * 给指定用户组发送群组消息
     *
     * @param userIds 待发送用户 openid
     * @param message 消息
     */
    void sentMessage(List<String> userIds, TextMessage message) throws IOException;

    /**
     * 给所有在线用户推送景区公告
     *
     * @param message 待发送消息
     * @return
     */
    void broadCast(TextMessage message) throws IOException;

    void handleError(WebSocketSession session, Throwable error);

    Set<WebSocketSession> getSessions();
    int getConnectionCount();
}
