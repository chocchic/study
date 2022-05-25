package com.chocchic.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer{
	
	private final long MAX_AGE_SECS = 3600;
	
	// CORS 설정을 위한 메서드
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 현재 애플리케이션의 모든 경로에 대해서
		registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
		.allowedHeaders("").allowCredentials(true).maxAge(MAX_AGE_SECS);
	}
}
