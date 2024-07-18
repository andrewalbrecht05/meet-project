package com.example.webserver;

public abstract class  ChatMessage {
    private String roomId;
    private String type;
    String username;
    String userID;
    public ChatMessage() {}


    public ChatMessage(String roomId,String type, String username) {
        this.roomId = roomId;
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public abstract String getContent();
    public abstract void setContent(String content);
    public String getMessageType(){
        return this.type;
    }
    public abstract String getUsername();
}
