package com.choc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.choc.app.MyInterceptor;

// web.xml이나 servlet-context.xml의 역할

@Component
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	// 인터셉터 등록하는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
	}
}
