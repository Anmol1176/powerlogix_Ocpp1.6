package com.powerlogix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.powerlogix.Handler.WebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer 
{

    private final WebSocketHandler webSocketHandler;

    @Autowired
    public WebSocketConfig(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) 
    {
        // Register your WebSocket handler with a specific URL mapping
        registry.addHandler(webSocketHandler, "/message");
    }
    
//    public void registerWebSocketHandlers1(WebSocketHandlerRegistry registry) 
//    {
//        // Register your WebSocket handler with a specific URL mapping
//        registry.addHandler(webSocketHandler, "/message1");
//    }

}
