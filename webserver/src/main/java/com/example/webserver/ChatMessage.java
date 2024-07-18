package com.example.webserver;

public abstract class  ChatMessage {
    private String roomId;

    public ChatMessage() {}

    public ChatMessage(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public abstract String getContent();
    public abstract void setContent(String content);
}
