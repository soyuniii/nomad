package com.web2.Chat.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
public class ChatMessageResponse {
    private String senderNickname;
    private String recipientNickname;
    private String content;
    private LocalDateTime sentAt;


}
