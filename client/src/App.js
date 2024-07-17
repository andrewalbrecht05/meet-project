import './App.css';
import { useEffect, useRef, useState } from "react";
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

function App() {
    const [messages, setMessages] = useState([]);
    const stompClientRef = useRef(null);

    useEffect(() => {
        connect();

        return () => {
            if (stompClientRef.current) {
                stompClientRef.current.deactivate();
            }
        };
    }, []);

    function connect() {
        const socket = new SockJS('http://localhost:8080/ws');
        const client = new Client({
            webSocketFactory: () => socket,
            debug: (str) => { console.log(str); },
            onConnect: (frame) => {
                console.log('Connected: ' + frame);
                client.subscribe('/topic/messages', (message) => {
                    const newMessage = JSON.parse(message.body).content;
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
            const message = document.getElementById('message').value;
            stompClientRef.current.publish({
                destination: "/app/message",
                body: JSON.stringify({
                    'content': message,
                    'roomId': 123
                })
            });
            document.getElementById('message').value = '';
        }
    }

    return (
        <div className="App">
            <div>
                <input id="message" type="text" placeholder="Type a message"/>
                <button onClick={sendMessage}>Send</button>
                <div id="messages">
                    {messages.map((msg, index) => (
                        <div key={index}>{msg}</div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default App;