package com.example.webserver;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StringMessage.class, name = "string"),
        @JsonSubTypes.Type(value = AudioMessage.class, name = "audio")
})
public abstract class  ChatMessage {
    private String roomId;
    String type;
    String username;
    String userID;
    public ChatMessage() {}


    public ChatMessage(String roomId,String type, String username) {
        this.roomId = roomId;
        this.type = type;
        this.username = username;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public abstract String getContent();
    public abstract void setContent(String content);
    public String getType(){
        return this.type;
    }
    public abstract String getUsername();
}
