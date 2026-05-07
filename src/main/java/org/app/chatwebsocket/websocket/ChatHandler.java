package org.app.chatwebsocket.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatHandler extends TextWebSocketHandler {


    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();// lista com as sessões dos clientes

    @Override
    public void afterConnectionEstablished(WebSocketSession session) { //quando alguém se conecta, entra nesse método
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        System.out.println("Mensagem recebida: " + message.getPayload());


        for (WebSocketSession s : sessions) {
            if (s.isOpen() && !s.getId().equals(session.getId())) { // evita de enviar a mensagem de volta para quem enviou
                s.sendMessage(message); // envia a mensagem para todos na sessão
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Quando o cliente fecha o programa, removemos da lista
        sessions.remove(session);
        System.out.println("Cliente desconectado. ID: " + session.getId());
    }
}