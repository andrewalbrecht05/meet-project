package com.example.webserver;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        // Додаємо ідентифікатор кімнати до заголовків, якщо потрібно
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
        return new ChatMessage(HtmlUtils.htmlEscape(message.getContent()), message.getRoomId());

    }
}
