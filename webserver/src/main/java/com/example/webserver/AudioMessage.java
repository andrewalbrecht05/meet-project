package com.example.webserver;

public class AudioMessage extends ChatMessage{
    private String content;

    public AudioMessage() {}

    public AudioMessage(String content, String roomId,String username) {
        super(roomId,"audio",username);
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
    @Override
    public String getUsername(){
        return this.username;
    }


}
