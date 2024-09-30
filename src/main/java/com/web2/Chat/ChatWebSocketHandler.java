package com.web2.Chat;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 핸들러 클래스
 * WebSocket을 통해 클라이언트 간의 메시지를 주고받는 로직 처리
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {

    // 사용자 닉네임을 기준으로 세션을 관리하는 맵
    private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userNickname = (String) session.getAttributes().get("userNickname");

        if (userNickname != null) {
            sessions.put(userNickname, session);
            System.out.println("닉네임: " + userNickname + " 세션 추가됨");
        } else {
            System.out.println("세션에 닉네임이 없습니다.");
        }
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 JSON 형태로 파싱
        String payload = message.getPayload();
        Map<String, String> messageData = new ObjectMapper().readValue(payload, Map.class);

        // 보낼 사람의 닉네임과 메시지 내용 가져오기
        String recipient = messageData.get("recipient");
        String sender = messageData.get("sender");
        String content = messageData.get("content");

        // 수신자의 세션을 찾아서 메시지 전송
        WebSocketSession recipientSession = sessions.get(recipient);
        if (recipientSession != null && recipientSession.isOpen()) {
            recipientSession.sendMessage(new TextMessage("{\"sender\":\"" + sender + "\", \"content\":\"" + content + "\"}"));
            System.out.println("메시지 전송 성공. 수신자: " + recipient);
        } else {
            System.out.println("수신자의 세션을 찾지 못했습니다. 수신자: " + recipient);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션이 종료되면 해당 닉네임의 세션을 맵에서 제거
        String userNickname = (String) session.getAttributes().get("userNickname");
        sessions.remove(userNickname);
    }
}
