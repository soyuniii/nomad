    package com.web2.user;

    import com.web2.global.SessionService;
    import com.web2.user.dto.LoginUser;
    import com.web2.user.dto.ResponseUserDto;
    import com.web2.user.dto.SignUser;
    import com.web2.user.dto.UserDTO;
    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.servlet.http.HttpSession;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.*;
    import org.springframework.web.bind.annotation.*;


    import java.util.HashMap;
    import java.util.Map;
    import java.util.UUID;

    @RequiredArgsConstructor
    @RestController
    public class UserController {

        public final UserService userService;
        public final SessionService sessionService;


        @PostMapping("/auth/sign")
        public ResponseEntity<ResponseUserDto> sign(@RequestBody SignUser Dto) { // 전달하고 싶은 DTO를 따로 생성해서 전달해도 됨
            ResponseUserDto frontdata = userService.sign(Dto);
            return ResponseEntity.ok(frontdata);  // 200 OK 상태 반환 닉네임을 반환

        }
        // 쿠키를 통해서 닉네임을 가져옴으로
        @PostMapping("/auth/login")
        public ResponseEntity<?> login(
                @RequestBody LoginUser Dto,
                HttpServletRequest request,
                HttpServletResponse response) {

            HttpSession session = request.getSession(false);  // 기존 세션 유지

            if (session != null && session.getAttribute("userNickname") != null) {
                // 이미 다른 세션이 존재할 경우
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("error", "이미 로그인된 세션이 있습니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            }

            String result = userService.login(Dto);

            if (result.equals("로그인 성공")) {
                session = request.getSession(true);  // 새로운 세션 생성
                User user = userService.mappingUser(Dto);
                ResponseUserDto nicknameResponse = new ResponseUserDto(user.getNickname());

                session.setAttribute("user", user);
                session.setAttribute("userNickname", user.getNickname());

                // CSRF 토큰 및 세션 만료 설정
                String csrfToken = UUID.randomUUID().toString();
                session.setAttribute("csrfToken", csrfToken);
                session.setMaxInactiveInterval(1800);

                // SESSION_ID 쿠키 설정
                Cookie sessionCookie = new Cookie("SESSION_ID", session.getId());
                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(true);  // HTTPS 환경에서만 사용할 때
                sessionCookie.setMaxAge(1800);
                sessionCookie.setPath("/");
                response.addCookie(sessionCookie);

                // userNickname 쿠키 설정
                Cookie nicknameCookie = new Cookie("userNickname", user.getNickname());
                nicknameCookie.setMaxAge(1800);  // 쿠키 만료 시간 30분
                nicknameCookie.setPath("/");
                response.addCookie(nicknameCookie);

                return ResponseEntity.ok(nicknameResponse);
            }

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "로그인 실패. 잘못된 이메일 또는 비밀번호입니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }


        @PostMapping("/auth/logout")
        public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("userNickname") != null) {
                session.invalidate();

                // SESSION_ID 쿠키 삭제
                Cookie sessionCookie = new Cookie("SESSION_ID", null);
                sessionCookie.setPath("/");
                sessionCookie.setMaxAge(0);
                sessionCookie.setHttpOnly(true);
                sessionCookie.setSecure(true);
                response.addCookie(sessionCookie);

                // userNickname 쿠키 삭제
                Cookie nicknameCookie = new Cookie("userNickname", null);
                nicknameCookie.setPath("/");
                nicknameCookie.setMaxAge(0);
                response.addCookie(nicknameCookie);

                return ResponseEntity.ok("로그아웃 되셨습니다.");
            }
            return ResponseEntity.badRequest().body("이미 로그아웃된 상태입니다.");
        }


       /*@PostMapping("/auth/login")
       public ResponseEntity<ResponseUserDto> login(@RequestBody LoginUser Dto, HttpSession session,
                                                    HttpServletRequest request, HttpServletResponse response) {
           // 로그인 시도
           userService.is_login(session); // 로그인을 처음하는 경우는 세션에 등록된 닉네임 없음

           String result = userService.login(Dto);
           if(result.equals("로그인 성공")){
               // 새로운 세션 생성
               session = request.getSession(true);
           }

           // 회원 매핑하기
           User user = userService.mappingUser(Dto);
           ResponseUserDto nickname = new ResponseUserDto(user.getNickname());

           session.setAttribute("user", user); // 회원 객체와 세션을 매핑
           session.setAttribute("userNickname", user.getNickname()); // 닉네임을 세션에 저장


           String csrfToken = UUID.randomUUID().toString(); // csrf 토큰을 생성
           session.setAttribute("csrfToken", csrfToken); // 세션에 csrfToken이 포함되도록 함
           session.setMaxInactiveInterval(1800); // 세션 만료시간을 30분으로 설정 1800

           Cookie sessionCookie = new Cookie("SESSION_ID", session.getId());
           sessionCookie.setHttpOnly(true); // 자바스크립트에서 접근 불가
           sessionCookie.setSecure(true); // https 환경에서만 쿠키 전달
           sessionCookie.setMaxAge(1800); // 쿠키의 만료 시간 30분
           sessionCookie.setPath("/");
           response.addCookie(sessionCookie); // 응답에 쿠키를 포함

           return ResponseEntity.ok(nickname);
       }*/



        /*@PostMapping("/auth/logout")
        public ResponseEntity<String> logout(HttpSession session, HttpServletResponse response) {
            // 사용자 세션 정보가 없으면 이미 로그아웃된 상태
            if (session.getAttribute("userNickname") == null) {
                return ResponseEntity.badRequest().body("이미 로그아웃 되었습니다.");
            } else {
                // 세션 무효화
                session.invalidate();

                // 클라이언트 측의 세션 쿠키 삭제
                Cookie sessionCookie = new Cookie("SESSION_ID", null); // 세션 ID를 null
                sessionCookie.setPath("/"); // 유효 경로 설정
                sessionCookie.setMaxAge(0); // 쿠키 만료 시간 0으로 설정 (즉시 삭제)
                sessionCookie.setHttpOnly(true); // HttpOnly 속성 유지
                sessionCookie.setSecure(true);   // HTTPS에서만 전송되는 속성 유지 (필요시)
                response.addCookie(sessionCookie); // 응답에 쿠키 추가

                return ResponseEntity.ok("로그아웃 되셨습니다.");
            }



        }*/

        @GetMapping("/my-profile")
        public UserDTO getuser(HttpSession session, @CookieValue(value = "SESSION_ID", required = false) String sessionId){
            sessionService.validateSession(sessionId,session);
            sessionService.validateCsrfToken(session);
            User user = sessionService.validateUser(session);

            UserDTO userDTO = userService.getprofile(user);
            return userDTO;

        }



    }
