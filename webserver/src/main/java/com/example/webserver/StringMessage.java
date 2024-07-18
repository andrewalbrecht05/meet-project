package com.example.webserver;

public class StringMessage extends ChatMessage{
    private String content;

    public StringMessage() {}

    public StringMessage(String content, String roomId) {
        super(roomId,"string");
        this.content = content;
    }
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
