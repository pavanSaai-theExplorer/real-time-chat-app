package com.realtime.chatApp.controller;

import com.realtime.chatApp.dto.ChatMessage;
import com.realtime.chatApp.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final RedisTemplate redisTemplate;

    // Adding User to Application
    @MessageMapping("/chat.user")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {

        // Getting userName from Chat Message Object
        // Adding to the Web Socket Session
        simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getUserName());

        chatMessage.setMessageType(MessageType.JOIN);
        chatMessage.setMessage(chatMessage.getUserName() + " joined the chat!");
        chatMessage.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        log.info("User joined : {} ", chatMessage.getUserName());

        // Sending chat Message back to Clients with message type as JSON
        redisTemplate.convertAndSend("chat", chatMessage);
        return chatMessage;
    }

    // Sending Message to the Clients
    @MessageMapping("/chat.send")
    public ChatMessage sendChatMessage(@Payload ChatMessage chatMessage) {

        // Adding Logic for sending message to the Redis DB
        chatMessage.setTimeStamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        redisTemplate.convertAndSend("chat", chatMessage);

        return chatMessage;
    }
}
