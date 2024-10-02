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
        String payload = message.getPayload(); // getPayload() 메소드를 통해서 텍스트의 내용을 읽어옴

        // ObjectMapper를 통해서 역직렬화 진행 - Map 형태로 변환
        // readValue(역직렬화하려는 내용 - 읽어온 메시지, 변환할 클래스 타입)
        Map<String, String> messageData = new ObjectMapper().readValue(payload, Map.class);

        // 아래와 같은 형식으로 값을 저장하기 위함임 !!! json의 형태와 자바의 map의 형태가 상당히 유사함
/*
        {
                "sender": "Alice",
                "recipient": "Bob",
                "content": "Hello!"
        }
*/

        // 보낼 사람의 닉네임과 메시지 내용 가져오기
        // 폼에서 입력해야할 양식 수신자, 송신자, 메시지 내용을 각각 읽어와서 문자열로 선언
        String recipient = messageData.get("recipient");
        String sender = messageData.get("sender");
        String content = messageData.get("content");

        // 수신자의 세션을 찾아서 메시지 전송
        // 수신자의 세션이 존재하고, 열려있을 때 sessions에 저장되어 있을 때
        // json 데이터로 파싱
        WebSocketSession recipientSession = sessions.get(recipient);
        if (recipientSession != null/* && recipientSession.isOpen()*/) {

            recipientSession.sendMessage(new TextMessage("{\"sender\":\"" + sender + "\", \"content\":\"" + content + "\"}")); // json으로 파싱
            System.out.println("메시지 전송 성공. 수신자: " + recipient);
        } else {
            System.out.println("수신자의 세션을 찾지 못했습니다. 수신자: " + recipient);
        }
    }
    // TextMessage는 전달할 메시지를 의미 내부에 json 형태를 정의 변수도 들어갈 수 있음
    // sendMessage 는 수신자의 세션에 메시지를 전달한다는 의미
    // \"는 json 파싱 시 "만 사용해서는 안되기 때문에 사용

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션이 종료되면 해당 닉네임의 세션을 맵에서 제거
        String userNickname = (String) session.getAttributes().get("userNickname");
        sessions.remove(userNickname);
    }
}
