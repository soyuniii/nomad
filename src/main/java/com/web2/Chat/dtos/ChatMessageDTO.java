package com.web2.Chat.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ChatMessageDTO {
    private String senderNickname;
    private String recipientNickname;
    private String content;

    public ChatMessageDTO(String senderNickname, String recipientNickname, String content) {
        this.senderNickname = senderNickname;
        this.recipientNickname = recipientNickname;
        this.content = content;
        // this.sentAt = sentAt; // 필요에 따라 추가
    }

}
