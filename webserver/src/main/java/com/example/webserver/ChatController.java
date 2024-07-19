package com.example.webserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.MessageMapping;
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
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
        System.out.println(message.getType());

        if ("string".equals(message.getType())) {
            StringMessage stringMessage = (StringMessage) message;
            String escapedContent = HtmlUtils.htmlEscape(stringMessage.getContent());
            messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), new StringMessage(escapedContent, message.getRoomId(), message.getUsername()));
        } else if ("audio".equals(message.getType())) {
            AudioMessage voiceMessage = (AudioMessage) message;
            messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), voiceMessage);
        }
    }
}
