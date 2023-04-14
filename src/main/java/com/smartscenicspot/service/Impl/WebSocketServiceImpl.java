package com.smartscenicspot.service.Impl;

import com.smartscenicspot.constant.RedisConstant;
import com.smartscenicspot.service.WebSocketService;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/4/3 19:10
 **/
public class WebSocketServiceImpl implements WebSocketService {

    /**
     * 在线连接数（线程安全）
     */
    private final AtomicInteger connectionCount = new AtomicInteger(0);

    /**
     * 线程安全的无序集合用于存储对话
     */
    private final CopyOnWriteArraySet<WebSocketSession> sessions
            = new CopyOnWriteArraySet<>();

    @Override
    public void handleOpen(WebSocketSession session) {
        sessions.add(session);
        connectionCount.incrementAndGet();
    }

    @Override
    public void handleClose(WebSocketSession session) {
        sessions.remove(session);
        connectionCount.decrementAndGet();
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        if(message instanceof PingMessage) {
            ByteBuffer buffer = ByteBuffer.allocate(1024).putInt(200);
            session.sendMessage(new PongMessage(buffer));
        }
    }


    @Override
    public void sentMessage(List<String> openids, TextMessage message) throws IOException {
        Optional<WebSocketSession> userSession = sessions.stream().filter(session -> {
            if(!session.isOpen()) {
                return false;
            }
            Map<String, Object> attributes = session.getAttributes();
            if(!attributes.containsKey(RedisConstant.USER_OPENID)) {
                return false;
            }
            String openid = (String) attributes.get(RedisConstant.USER_OPENID);
            return openids.contains(openid);
         }).findFirst();

        if(userSession.isPresent()) {
            userSession.get().sendMessage(message);
        }
    }


    @Override
    public void broadCast(TextMessage message) throws IOException {
        for (WebSocketSession session: sessions) {
            if(session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }

    @Override
    public void handleError(WebSocketSession session, Throwable error) {
        handleClose(session);
        System.out.println(error.getMessage());
    }

    @Override
    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    public int getConnectionCount() {
        return connectionCount.get();
    }
}
