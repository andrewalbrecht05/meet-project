/package com.example.webserver;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/message")
    public void sendMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        // Додаємо ідентифікатор кімнати до заголовків, якщо потрібно
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());

        // Екрануємо HTML у повідомленні для запобігання XSS-атакам
        String escapedContent = HtmlUtils.htmlEscape(message.getContent());

        // Відправляємо повідомлення до динамічної теми, що відповідає roomId
        messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), new ChatMessage(escapedContent, message.getRoomId()));
    }
}
