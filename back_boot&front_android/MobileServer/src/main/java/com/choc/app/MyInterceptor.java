package com.choc.app;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{
	// 콘솔에 로그를 출력하기 위한 객체
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// Controller의 요청 처리 메서드를 호출하기 전에 호출되는 메서드
	// true를 리턴하면 Controller의 요청 처리 메서드를 호출하고 false를 리턴하면 Controller의 메서드를 호출하지 않음
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("PreHandle");
		// 일 단위로 파일을 생성
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		PrintWriter pw = new PrintWriter(new FileOutputStream(str+".log", true));
		pw.println(request.getRemoteAddr() + ":" + request.getMethod() 
		+ ":"+ request.getMethod() + ":"+request.getRequestURI());
		pw.flush();// 버퍼의 내용울 전송
		
		pw.close();
		
		return true;
	}
	
	// Controller가 정상적으로 요청을 처리한 후 호출 되는 메서드
	// ModelAndView객체를 이용해서 데이터를 변환할 수있음
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelandView) throws Exception {
		logger.info("PostHandler");
	}
	
	// Controller에서 메서드를 수행하고 난 후 예외 발생 여부에 상관없이 호출되는 메서드
	// ex이 null이 아니면 예외가 발생한 것임
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		logger.info("afterCompletion");
	}
}
