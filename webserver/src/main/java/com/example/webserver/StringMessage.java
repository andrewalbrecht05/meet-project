package com.example.webserver;

public class StringMessage extends ChatMessage {
    private String content;

    public StringMessage() {
        super();
        setType("string");
    }

    public StringMessage(String content, String roomId, String username) {
        super(roomId, "string", username);
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
