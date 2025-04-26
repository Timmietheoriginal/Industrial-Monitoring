package com.example.Industrial_monitoring.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Component
public class WebSocketAlertHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        sessions.add(session);
        session.sendMessage(new TextMessage("Connected to Websocket Server"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
        session.sendMessage(new TextMessage("Received: " + message.getPayload()));
    }

    public void sendAlert(String alertMessage){
        for(WebSocketSession session : sessions){
            try{
                session.sendMessage(new TextMessage(alertMessage));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
