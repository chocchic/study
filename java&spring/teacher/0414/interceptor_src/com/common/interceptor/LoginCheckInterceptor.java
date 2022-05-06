package com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import lombok.extern.log4j.Log4j;

/*
	# 스프링 인터셉터란?
	서블릿은 자바 웹 표준에서 제공하는 기능이라면, 인터셉터는 스프링 MVC에서만 제공하는 기능이다.
	
	요청 -> WAS -> 필터 -> 서블릿(Dispatcher Servlet) -> 스프링 인터셉터 -> 컨트롤러 ...
	
		public interface HandlerInterceptor {
			// 컨트롤러 요청 전
			default boolean preHandle(HttpServletRequest request, HttpServletResponse response,
				Object handler) throws Exception {
			}
			 
			// 컨트롤러 호출 이후 (뷰에 전달할 Model 객체 제공)
			default void postHandle(HttpServletRequest request, HttpServletResponse response,
				Object handler, @Nullable ModelAndView modelAndView) throws Exception {
			}
			 
			// 완전하게 HTTP 요청이 끝난 이후 (Exception 제공)
			default void afterCompletion(HttpServletRequest request, HttpServletResponse response,
				Object handler, @Nullable Exception ex) throws Exception {
			}
		}
	
	* preHandle 는 컨트롤러에 가기전에 호출되는 메서드이다.
			preHandle 반환 값이 true 이면 다음 인터셉터를 호출하고, false이면 컨트롤러를 바로 호출한다.
	* postHandle 는 컨트롤러의 return 이후 호출되는 메서드이다.
		뷰에 전달할 Model을 조작할 수 있다.
		postHandle 는 컨트롤러에 예외가 발생하면 호출되지 않지만 afterCompletion은 예외와 상관없이 항상 호출된다.
	* afterCompletion 은 HTML 뷰가 렌더링된 이후 호출되는 메서드이다.
	
	1. 인터셉터 객체 만들기
		서블릿 필터와 비슷하게 사용할 수 있다. HandlerInterceptor 인터페이스를 구현하면 된다.
		인터셉터 또한 스프링 빈으로 등록된다. 즉 싱글톤이므로 클래스 내에 필드(상태값)을 사용해서는 안된다.
		
	* 참고로 preHandle(... Object handler)는 스프링MVC가 사용하는 핸들러의 모든 정보를 담고있다.
		HandlerMethod로 캐스팅하면 컨트롤러 빈 객체와 RequestMapping 메서드 정보를 얻을 수 있다.
		ResourceHttpRequestHandler로 캐스팅하면 요청 HTTP 헤더와 같은 정적 리소스 정보를 얻을 수 있다.	
		Object handler가 어떻게 생성된건지 이해하려면 스프링 MVC의 Dispatcher Servlet의 동작을 이해하면 된다.
			Dispatcher Servlet 에서 URI 요청에 맞는 핸들러를 찾고, 핸들러 어댑터로 컨트롤러를 실행한다.
			Object handler는 선택된 컨트롤러 객체와 컨트롤러 객체에 전달할 HTTP 요청데이터를 가지고 있다. 
	
	2. 인터셉터를 스프링빈으로 등록 & 설정 
		인터셉터도 필터와 마찬가지로 다양한 설정을 해줄 수 있다. 사용법도 비슷한 편.
		Dispatcher 가 HandlerInterceptor를 호출해주므로 servlet-context.xml에 인터셉터 설정해주자 
		
		<beans:bean id="loginInterceptor" class="com.common.interceptor.LoginInterceptor" />
		<interceptors>
			<interceptor>
				<mapping path="/member/mypage"/>
				<beans:ref bean="loginInterceptor" />
			</interceptor>
		</interceptors>
		
	..3. 가던길 가는것을 하려면 로그인만 따로 인터셉터 처리 해주고 원래 가려던 주소는 여기서 session에 저장해서 
			로그인처리 interceptor에서 이어주기. 

*/
@Log4j
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("**** 로그인 확인 인터셉터 ****");
		String requestURI = request.getRequestURI(); 
		log.info("preHandler Request URI : " + requestURI);

		HttpSession session = request.getSession(); 
		if(session == null || session.getAttribute("memId") == null) { // 비로그인시 
			// 데이터 보내주고 싶을 때 추가 
			FlashMap flashMap = new FlashMap(); 
			flashMap.put("msg", "invalid session");
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			/* 보내준 메세지 사용 : login.jsp 상단에 아래 스크립트 추가
				<script>
					let msg = '${msg}';
					console.log(msg);
					if(msg) {
						alert(msg + " : 로그인 해주세요..."); 
					}
				</script>
			*/
			
			/* 3. 로그인폼으로 이동하면, login pro 처리하고 main으로 이동하는데, 
			   main이 아닌 가던길 가고싶을때 
			String prevPage = requestURI + "?" + request.getQueryString(); 
			request.getSession().setAttribute("prevPage", prevPage);
			
			+ MemberController > PostMapping "login" 메서드안에 아래 메서드 추가, 매개변수 session 추가 

				int result = memberSe.... (기존코드) 

				String prevPage = null; 
				if(session.getAttribute("prevPage") != null) {
					prevPage = (String)session.getAttribute("prevPage"); 
					session.removeAttribute("prevPage");
				}
				if(prevPage != null) {
					return "redirect:"+prevPage;
				}
				
				model.addAttribute("result"....  (기존코드)
			*/
			
			response.sendRedirect("/member/login"); // -> 로그인 폼으로 redirect 
			return false; // 다음 인터셉터 실행안되고, 컨트롤러 실행 
		}
		return true; // 다음 인터셉터 실행
	}
	
	// pre다음으로 이어지는 인터셉트들로 위에서 return 컨트롤러로 이동하면 post,afterCompletion 메서드는 실행안함 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		log.info("post");  
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.info("after");
		super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
