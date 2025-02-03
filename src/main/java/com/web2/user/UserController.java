package com.web2.user;

import com.web2.global.SessionService;
import com.web2.user.dto.LoginUser;
import com.web2.user.dto.SignUser;
import com.web2.user.dto.UserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    @PostMapping("/auth/sign")
    public ResponseEntity<String> sign(@RequestBody SignUser Dto) { // 전달하고 싶은 DTO를 따로 생성해서 전달해도 됨
        String result = userService.sign(Dto);
        return ResponseEntity.ok(result);  // 200 OK 상태 반환
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginUser Dto,
                                        HttpSession session,
                                        HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger(getClass());

        String result = userService.login(Dto);

        User user = userService.findUserByEmail(Dto.email());
        session.setAttribute("user", user); //세션에 user 객체 저장

        String csrfToken = UUID.randomUUID().toString(); // csrf 토큰을 통해서 사용자가 보낸 요청이 아닐 경우는 처리하지 않도록 함
        session.setAttribute("csrfToken", csrfToken); // 세션에 csrfToken이 포함되도록 함
        session.setMaxInactiveInterval(1800); // 세션 만료시간을 30분으로 설정

        Cookie sessionCookie = new Cookie("SESSION_ID", session.getId());
        sessionCookie.setHttpOnly(false); // 자바스크립트에서 접근 불가
        sessionCookie.setSecure(false); //true일 때 HTTPS에서만 쿠키가 전송
        sessionCookie.setMaxAge(1800); // 쿠키의 만료 시간 30분
        sessionCookie.setPath("/");
        sessionCookie.setAttribute("SameSite", "None");

        response.addCookie(sessionCookie); // 응답에 쿠키를 포함

        logger.info("SESSION_ID: {}", session.getId());

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
            Cookie sessionCookie = new Cookie("SESSION_ID", null); // 세션 ID를 null로 설정
            sessionCookie.setPath("/"); // 유효 경로 설정
            sessionCookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정 (즉시 삭제)
            /*sessionCookie.setHttpOnly(true); // HttpOnly 속성 유지*/
            sessionCookie.setSecure(true);   // HTTPS에서만 전송되는 속성 유지 (필요시)
            response.addCookie(sessionCookie); // 응답에 쿠키 추가

            return ResponseEntity.ok("로그아웃 되셨습니다.");
        }
    }

    //프로필 조회
    //세션에서 user_id 가져오는 걸로 수정하기
    @GetMapping("/my-profile")
    public UserDTO getUser(HttpSession session,
                           @CookieValue(value = "SESSION_ID", required = false) String sessionId) {

        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        UserDTO userDTO = userService.getprofile(user);

        return userDTO;
    }


}
