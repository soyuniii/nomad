package com.web2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/auth/login")
    public String showLoginForm() {
        return "login"; // 로그인 페이지로 이동하는 템플릿 파일 이름
    }
}
