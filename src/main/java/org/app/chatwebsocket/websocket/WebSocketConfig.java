package org.app.chatwebsocket.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // O caminho que o cliente vai usar para conectar
        registry.addHandler(new ChatHandler(), "/chat")
                .setAllowedOriginPatterns("*"); // Permite conexão de qualquer origem (inclusive do ngrok depois)
    }
}