package com.web2.Friends;

import com.web2.user.User;
import com.web2.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;
    private final UserRepository userRepository;

    public FriendsController(FriendsService friendsService, UserRepository userRepository) {
        this.friendsService = friendsService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add/{userId}/{friendId}")
    public ResponseEntity<String> addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        friendsService.addFriend(user, friendId);
        return ResponseEntity.ok("친구가 추가되었습니다.");
    }

    @DeleteMapping("/remove/{userId}/{friendId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        friendsService.removeFriend(user, friendId);
        return ResponseEntity.ok("친구가 삭제되었습니다.");
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<String>> getFriendsList(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        List<String> friendsList = friendsService.getFriendsList(user).stream()
                .map(f -> f.getFriend().getNickname() + " (" + f.getFriend().getNationality() + ")")
                .collect(Collectors.toList());
        return ResponseEntity.ok(friendsList);
    }
}
