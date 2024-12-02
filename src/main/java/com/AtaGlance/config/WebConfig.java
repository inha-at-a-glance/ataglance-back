package com.AtaGlance.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // API 경로 지정
                .allowedOriginPatterns("*")
                //.allowedOrigins("http://localhost:3000", "http://192.168.56.1:3000") // React 개발 서버
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true); // 쿠키 및 인증정보 허용
    }
}
