package com.web2.Chat;

import com.web2.Chat.dtos.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class ChatController {

    private final ChatService chatService;

    @GetMapping("/messages")
    public ResponseEntity<List<ChatMessageDTO>> getMessages(
            @RequestParam String sender,
            @RequestParam String recipient) {
        List<ChatMessageDTO> messages = chatService.getMessagesBetween(sender, recipient);
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
