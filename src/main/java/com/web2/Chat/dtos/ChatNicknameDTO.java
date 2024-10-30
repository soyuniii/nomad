package com.web2.Chat.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatNicknameDTO {

    private String senderNickname;
    private String recipientNickname;

}
