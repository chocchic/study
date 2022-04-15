package com.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.log4j.Log4j;

// sec#7. 접근제한 처리 클래스 생성 
@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	// 메서드 구현 
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		log.error("Access Denied Handler!!");
		log.error("redirect......");
		
		response.sendRedirect("/common/accessError");
		
	}

	
	
}
