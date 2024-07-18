package com.example.webserver;

public class AudioMessage extends ChatMessage{
    private String content;

    public AudioMessage() {}

    public AudioMessage(String content, String roomId) {
        super(roomId);
        this.content = content;
    }
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content){
        this.content = content;
    }


}
