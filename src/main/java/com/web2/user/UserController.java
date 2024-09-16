package com.web2.user;

import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<String> sign(@RequestBody SignUser Dto) { // 전달하고 싶은 DTO를 따로 생성해서 전달해도 됨
        String result = userService.sign(Dto);
        if ("회원가입 성공".equals(result)) {
            return ResponseEntity.ok(result);  // 200 OK 상태 반환
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);  // 409 Conflict 상태 반환
        }
    }

    @PostMapping("auth/login")
    public ResponseEntity<String> login(@RequestBody LoginUser Dto, HttpSession session){
        String result = userService.login(Dto);

        if("로그인 성공".equals(result)){
            session.setAttribute("user_id",userService.returnID(Dto)); // ID를 받아오는 것이 대체적으로 좋음 그렇다면.. 아이디를 받을 수 있는 DTO가..
            session.setMaxInactiveInterval(1800);
            
            // 보안 관련 부분 추가하기
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    // 로그 아웃 기능도 필요하지 않나?





    // 서비스에서 중복된 이메일을 검사, 비밀번호의 조건 추가, 등등의 메소드를 정의하면 될거 같다
    // 리포지터리에서 정의할 메소드를 생각해보자


    // 세션을 통한 로그인 구현, 세션이란 쿠키를 통해서 인증된 사용자에게 세션 ID를 가지게 하고
    // 로그아웃 시 삭제하여 보안을 유지한다 세션을 사용하는 이유는 세션이 서버에 저장되기 때문인데
    // 쿠키를 사용하여 관리자 계정으로 로그인하게 된다면 쿠키는 사용자의 웹 브라우저에 존재하여 탈취에 상당히 취약해진다
    // 세션 ID 를 UUID를 사용해서 임의로 인가 받고 이것을 클라이언트에게 전달하는 일련의 과정을 처리해야 한다 -- 서비스 계층에서
    // httpsession을 통해서 일단 세션 값을 무작위로 생성하고 파기함, 세션의 만료 시간을 설정,



}

