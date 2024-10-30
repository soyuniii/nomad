package com.web2.user;

import com.web2.user.dto.SimpleUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class Getcontroller {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<SimpleUserResponse>> getAllUsers() {
        List<SimpleUserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
