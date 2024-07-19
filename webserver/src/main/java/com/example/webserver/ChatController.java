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
    //private final ObjectMapper objectMapper;


    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        //this.objectMapper = objectMapper;
    }

    @MessageMapping("/message")
    public void sendMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        /*// Додаємо ідентифікатор кімнати до заголовків, якщо потрібно
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());

        // Екрануємо HTML у повідомленні для запобігання XSS-атакам
        String escapedContent = HtmlUtils.htmlEscape(message.getContent());

        // Відправляємо повідомлення до динамічної теми, що відповідає roomId
        messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), new ChatMessage(escapedContent, message.getRoomId()));
        //System.out.println("Sent message: " + message.getContent());
         */
        headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
        System.out.println(message.getType());
        if ("string".equals(message.getType())) {
            StringMessage stringMessage = (StringMessage) message;
            String escapedContent = HtmlUtils.htmlEscape(stringMessage.getContent());
            messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), new StringMessage(escapedContent, message.getRoomId(), message.getUsername(),"string"));
        } else if ("audio".equals(message.getType())) {
            AudioMessage voiceMessage = (AudioMessage) message;
            // Обробка голосового повідомлення, наприклад, збереження або передача іншим користувачам
            messagingTemplate.convertAndSend("/topic/messages/" + message.getRoomId(), voiceMessage);
        }
    }

}