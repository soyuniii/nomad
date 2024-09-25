package com.web2.global;

import com.web2.user.UnauthorizedException;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    // csrf 토큰이 제대로 발행되었는지
    // 클라이언트 측으로 전달되는 쿠키에 사용자의 정보가 매핑되었는지
    public void validateSession(String sessionId, HttpSession session) {
        if(sessionId == null || !sessionId.equals(session.getId())) {
            throw new UnauthorizedException("잘못된 세션입니다. 다시 로그인해주세요.");
        }
    }

    public void validateCsrfToken(HttpSession session) {
        String csrfToken = (String) session.getAttribute("csrfToken");
        if (csrfToken == null) {
            throw new UnauthorizedException("사용자 인증이 필요합니다.");
        }
    }

    public User validateUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UnauthorizedException("로그인 후 이용해주세요.");
        }
        return user;
    }
}




