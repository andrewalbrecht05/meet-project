package com.example.webserver;

public class StringMessage extends ChatMessage{
    private String content;

    public StringMessage() {}

    public StringMessage(String content, String roomId, String username, String type) {
        super(roomId,type, username);
        this.content = content;
        this.type = type;
    }
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public String getUsername(){
        return this.username;
    }
    public String getType() {
        return this.type;
    }
}
