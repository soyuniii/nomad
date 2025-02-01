package com.web2.Chat;

import com.web2.Chat.dtos.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessageDTO>> getMessages(
            @RequestParam String senderNickname,
            @RequestParam String recipientNickname) {
        List<ChatMessageDTO> messages = chatService.getMessagesBetween(senderNickname, recipientNickname);
        return ResponseEntity.ok(messages);
    }


    @PostMapping("/messages")
    public ResponseEntity<Void> sendMessage(
            @RequestBody ChatMessageDTO chatMessageDTO) {
        chatService.saveMessage(
                chatMessageDTO.getSenderNickname(),
                chatMessageDTO.getRecipientNickname(),
                chatMessageDTO.getContent()
        );
        return ResponseEntity.ok().build();
    }
}
