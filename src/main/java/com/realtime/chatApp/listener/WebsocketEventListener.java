package com.realtime.chatApp.listener;

import com.realtime.chatApp.dto.ChatMessage;
import com.realtime.chatApp.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebsocketEventListener {
    private final RedisTemplate<String, Object> redisTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection...");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor simpMessageHeaderAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());

        String username = (String) simpMessageHeaderAccessor.getSessionAttributes().get("username");

        if (username != null) {
            ChatMessage message = new ChatMessage();
            message.setMessageType(MessageType.LEAVE);
            message.setUserName(username);
            message.setMessage(username + " left the chat :(");
            log.info("User disconnected: {} ", username);
            redisTemplate.convertAndSend("chat", message);
        }
    }
}