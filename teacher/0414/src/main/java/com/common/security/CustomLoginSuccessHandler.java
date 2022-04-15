package com.common.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

// 로그인시 특정 동작 처리를 위한 클래스 생성
// 	ex) 권한에 따른 다른 페이지로 이동처리 하겠다... 
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// Authentication 매개변수 객체를 이용해, 해당 사용자가 가진 모든 권한을 문자열로 체크 가능. 
		
		log.info("Login Success");
		
		List<String> roleNames = new ArrayList<>();
		
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		log.warn("ROLE NAMES : " + roleNames);
		
		/* 사용자가 ADMIN 권한을 가지고 있으면 
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/sample/admin"); // 해당 URI로 이동 하고 메서드 종료 
			return;
		}
		
		// 사용자가 MEMBER 권한을 가지고 있으면 
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect("/sample/member");	// 해당 URI로 이동하고 메서드 종료 
			return; 
		} */
		
		// 로그인 폼 이동전에 보던 페이지로 다시 돌아가기 
		HttpSession session = request.getSession(); 
		if(session != null) {
			String redirectUrl = (String)session.getAttribute("prevPage"); 
			if(redirectUrl != null) {
				session.removeAttribute("prevPage");
				response.sendRedirect(redirectUrl);
			}else {
				response.sendRedirect("/common/main");
			}
		}else {
			response.sendRedirect("/common/main");
		}
		
	}
	
}
