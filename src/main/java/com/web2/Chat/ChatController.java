package com.web2.Chat;

import com.web2.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;


/**
 * 채팅 페이지 접근 제어
 * 로그인한 사용자만 /chat 경로에 접근 가능하도록 설정
 */
@Controller
public class ChatController {

    //임시 뷰
    @GetMapping("/chat")
    public String chat(HttpSession session, Model model) {
        // 세션에서 로그인된 사용자가 있는지 확인
        User loggedUser = (User) session.getAttribute("user");

        // 사용자가 로그인되지 않았으면 로그인 페이지로 리다이렉트
        if (loggedUser == null) {
            return "redirect:/login";
        }

        // 로그인된 사용자의 닉네임을 모델에 추가
        model.addAttribute("nickname", loggedUser.getNickname());
        return "chat"; // chat.html 반환
    }
}


