import './App.css';
import React, {useEffect, useRef, useState} from "react";
import {Client} from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { IoSend } from "react-icons/io5";
import ChatMessage from "./ChatMessage/ChatMessage";
import * as moment from 'moment';

function App() {
    const [messages, setMessages] = useState([]);
    let nameInputRef = useRef(null);
    const stompClientRef = useRef(null);
    //const roomId = useRef(Math.floor(Math.random() * 100));
    const roomId = useRef(69);

    useEffect(() => {
        connect();
        return () => {
            if (!nameInputRef.current)
              nameInputRef.current = prompt("Enter your name:");

            let messageIcon = document.getElementById("message__icon");
            document.addEventListener("keypress", (e) => {
                console.log(e.key);
                if (e.key === "Enter") {
                    sendMessage();
                }
            });
            messageIcon.addEventListener("click", (e) => {
                sendMessage();
            });
            if (stompClientRef.current) {
                stompClientRef.current.deactivate();
            }
        };
    }, []);


    function connect() {
        const socket = new SockJS('http://localhost:8080/ws');
        const client = new Client({
            webSocketFactory: () => socket,
            debug: (str) => {
                console.log(str);
            },
            onConnect: (frame) => {
                console.log('Connected: ' + frame);
                client.subscribe(`/topic/messages/${roomId.current}`, (message) => {
                    const newMessage = {
                        'text': JSON.parse(message.body).content,
                        'timestamp': moment().format('h:mm:ss a'),
                        'username': JSON.parse(message.body).username, // change from server
                    };

                    console.log(message.body);
                    setMessages(prevMessages => [...prevMessages, newMessage]);
                });
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
        });
        client.activate();
        stompClientRef.current = client;
    }

    function sendMessage() {
        if (stompClientRef.current) {
            const message = document.getElementById('message__field').value;
            console.log(nameInputRef.current);
            stompClientRef.current.publish({
                destination: "/app/message",
                body: JSON.stringify({
                    'type': "string",
                    'username': nameInputRef.current,
                    'content': message,
                    'roomId': roomId.current
                })
            });
            document.getElementById('message__field').value = '';
        }
    }

    return (
        <div className="App">
            <div id="messages">
                {messages.map((msg, index) => (
                    <ChatMessage key={index} sender={msg.username} message={msg.text} timestamp={msg.timestamp} isMyMessage={msg.username === nameInputRef.current} />
                ))}
            </div>
            <div className="message">
                <input id="message__field" type="text" placeholder="Type a message"/>
                <IoSend id="message__icon"/>
            </div>
        </div>
    );
}

export default App;