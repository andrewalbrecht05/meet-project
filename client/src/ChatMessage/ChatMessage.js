import React from 'react';
import './ChatMessage.css';

const ChatMessage = ({ message, sender, timestamp, isMyMessage }) => {
    console.log(message);
    const x = isMyMessage ? "chat-message right" : "chat-message left";
    return (
        <div className={x}>
            <div className="chat-message-header">
                <span className="chat-message-sender">{sender}</span>
                <span className="chat-message-timestamp">{timestamp}</span>
            </div>
            <div className="chat-message-body">{message}</div>
        </div>
    );
};

export default ChatMessage;
