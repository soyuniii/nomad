package com.web2.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController {

    public final UserService userService;

    @PostMapping("/auth/sign") 
    public ResponseEntity<String> sign(@RequestBody SignUser Dto) { // 전달하고 싶은 DTO를 따로 생성해서 전달해도 됨
        String result = userService.sign(Dto);
        return ResponseEntity.ok(result);  // 200 OK 상태 반환

    }

    @PostMapping("auth/login")
    public ResponseEntity<String> login(@RequestBody LoginUser Dto, HttpSession session, HttpServletResponse response){
        String result = userService.login(Dto);

        String csrfToken = UUID.randomUUID().toString(); // csrf 토큰을 통해서 사용자가 보낸 요청이 아닐 경우는 처리하지 않도록 함
        session.setAttribute("csrfToken", csrfToken); // 세션에 csrfToken이 포함되도록 함
        session.setMaxInactiveInterval(1800); // 세션 만료시간을 30분으로 설정

        Cookie sessionCookie = new Cookie("SESSION_ID", session.getId());
        sessionCookie.setHttpOnly(true); // 자바스크립트에서 접근 불가
        sessionCookie.setSecure(true); // https 환경에서만 쿠키 전달
        sessionCookie.setMaxAge(1800); // 쿠키의 만료 시간 30분
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie); // 응답에 쿠키를 포함


        return ResponseEntity.ok(result);

        }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) {
        // 사용자 세션 정보가 없으면 이미 로그아웃된 상태
        if (session.getAttribute("csrfToken") == null) {
            return ResponseEntity.badRequest().body("이미 로그아웃 되었습니다.");
        } else {
            // 세션 무효화
            session.invalidate();

            // 클라이언트 측의 세션 쿠키 삭제
            Cookie sessionCookie = new Cookie("SESSIONID", null); // 세션 ID를 null로 설정
            sessionCookie.setPath("/"); // 유효 경로 설정
            sessionCookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정 (즉시 삭제)
            sessionCookie.setHttpOnly(true); // HttpOnly 속성 유지
            sessionCookie.setSecure(true);   // HTTPS에서만 전송되는 속성 유지 (필요시)
            response.addCookie(sessionCookie); // 응답에 쿠키 추가

            return ResponseEntity.ok("로그아웃 되셨습니다.");
        }
    }







}
