package com.web2.global;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

// ngrok config add-authtoken 2t49nxGlRMoKSY2IOWRAYHsmy20_85oqxM6y2hnuF4afTKdjx
@Controller
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("auth/login")
    public String login() {
        return "login";
    }
    @GetMapping("auth/sign")
    public String sign() {
        return "sign";
    }
    @GetMapping("auth/logout")
    public String logout() {
        return "logout";
    }

}
