package com.example.webserver;

public class ChatMessage {
    private String content;
    private String roomId;

    public ChatMessage() {}

    public ChatMessage(String content, String roomId) {
        this.content = content;
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
