package com.web2.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    public final UserService userService;    


    @PostMapping("/auth/sign")
    public ResponseEntity<String> sign(@RequestBody SignUser Dto) {
        String result = userService.sign(Dto);
        if ("회원가입 성공".equals(result)) {
            return ResponseEntity.ok(result);  // 200 OK 상태 반환
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);  // 409 Conflict 상태 반환
        }
    }
    @PostMapping("auth/login")
    public ResponseEntity<String> login(@RequestBody LoginUser Dto){
        String result = userService.login(Dto);
        if("로그인 성공".equals(result)){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


    // 서비스에서 중복된 이메일을 검사, 비밀번호의 조건 추가, 등등의 메소드를 정의하면 될거 같다
    // 리포지터리에서 정의할 메소드를 생각해보자




}

