package com.web2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    public final UserService userService;    
    
    @PostMapping("/auth/sign")
    public SignUser SignUser(@RequestBody SignUser DTO) {
        return userService.method(DTO); // 메소드 정의해서 집어넣으면 됨
    }
    // 서비스에서 중복된 이메일을 검사, 비밀번호의 조건 추가, 등등의 메소드를 정의하면 될거 같다
    // 리포지터리에서 정의할 메소드를 생각해보자




}

