import React, { useState } from 'react';
import SockJsClient from 'react-stomp';

const WEBSOCKET_URL = 'http://localhost:8080/ws';
const TOPIC = ['/topic/messages'];

const App = () => {
  const [messages, setMessages] = useState([]);

  let onConnected = () => {
    console.log("Connected!!")
  }

  let onMessageReceived = (msg) => {
    console.log("message received");
    console.log(msg)
    setMessages([
      ...messages,
      msg
    ]);
  }

  return (
    <div>
      <SockJsClient
        url = {WEBSOCKET_URL}
        topics = {TOPIC}
        onConnect = {onConnected}
        onMessage = {msg => onMessageReceived(msg)}
      />
      <ul>
        {messages.map((msg, index) => (
          <li key={index}>{JSON.stringify(msg, null, 2 )}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;