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
public abstract class ChatMessage {
    private String roomId;
    private String type;
    private String username;
    private String userID;

    public ChatMessage() {}

    public ChatMessage(String roomId, String type, String username) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public abstract String getContent();
    public abstract void setContent(String content);
}
