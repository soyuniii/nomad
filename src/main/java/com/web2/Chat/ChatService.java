package com.web2.Chat;

import com.web2.Chat.dtos.ChatMessageDTO;
import com.web2.user.User;
import com.web2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public List<ChatMessageDTO> getMessagesBetween(String senderNickname, String recipientNickname) {
        User sender = userRepository.findByNickname(senderNickname)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findByNickname(recipientNickname)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        List<Message> messages = messageRepository.findBySenderAndRecipient(sender, recipient);

        return messages.stream()
                .map(message -> new ChatMessageDTO(
                        message.getSender().getNickname(),
                        message.getRecipient().getNickname(),
                        message.getContent()
                ))
                .collect(Collectors.toList());
    }

    public void saveMessage(String senderNickname, String recipientNickname, String content) {
        User sender = userRepository.findByNickname(senderNickname)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findByNickname(recipientNickname)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        Message message = new Message(sender, recipient, content);
        messageRepository.save(message);
    }
}
