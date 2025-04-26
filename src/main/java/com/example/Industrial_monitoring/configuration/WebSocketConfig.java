package com.example.Industrial_monitoring.configuration;

import com.example.Industrial_monitoring.websocket.WebSocketAlertHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketAlertHandler alertHandler;

    public WebSocketConfig(WebSocketAlertHandler alertHandler){
        this.alertHandler = alertHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(alertHandler, "/alert").setAllowedOrigins("*");
    }

}
