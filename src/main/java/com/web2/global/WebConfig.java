package com.web2.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 허용
                /*.allowedOrigins("http://localhost:3000",
                        "https://f97b-210-113-245-178.ngrok-free.app") // 허용할 프론트엔드 URL (예: localhost:3000)*/
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 지정
                .allowCredentials(true) // 쿠키 사용을 허용
                .maxAge(3600); // 캐싱 시간 설정 (초 단위)
    }
}
