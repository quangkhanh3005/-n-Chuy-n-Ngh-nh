package com.example.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Đường dẫn cho phép
                .allowedOrigins("http://localhost:3000") // Nguồn cho phép
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức cho phép
                .allowCredentials(true);
    }
}
