package com.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 컨트롤러 역할을 할 클래스다~ 선언
@RequestMapping("/sample/") // 클래스 레벨에 경로 추가
							//이 클래스 안에 메서드들의 경로 전에 모두 /sample/경로가 추가됨.
public class SampleController {
	
	//RM에 요청주소만 작성하면 GET방식, POST방식 모두 받아줌
	//http://localhost:8080/hello 으로 실행하면 나옴
	@RequestMapping("hello")
	public String hello() {
		
		System.out.println("hello page 요청");
		// hello 요청에 해당하는 비즈니스 로직 처리할 객체의 메서드 호출 (로직 처리 해라~)
		
		//return "jsp페이지파일명"; view폴더 내에 있는 파일 부르는 거라 경로랑 확장자 작성 X
		return "day02/test"; // 보여줘야 하는 jsp페이지 리턴
		// viewresolver가 WEB-INF의 views를 자동으로 경로로 잡아줌
		// 만약 없었다면 전체경로를 아래와 같이 입력해주어야함
		// return "/WEB-INF/views/day02/test.jsp";
	}
	// GET 방식의 요청만 받음 value="요청주소", method=요청방식
	/* 	C 	Create  Post
	 *  R	Read	GET
	 *  U	Update	PUT
	 * 	D	Delete	DELETE
	 */
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test() {
		System.out.println("test 요청됨");
		
		return "day02/test";
	}
	
	@GetMapping("test2") // Get요청만 받는 어노테이션 (스프링 4.3버전부터 사용가능)
	public String test2() {
		return "day02.test";
	}
	
	@PostMapping("test3") // Post요청만 받는 어노테이션
	public String test3() { 
		return "day02/test";
	}
	
	// 여러 경로 한번에 적용
	@GetMapping({"test4","test5"})
	public String test4() {
		System.out.println("test4 요청!");
		return "day02/test";
	}
	
//	@GetMapping("sample?")
//	public String test6() {
//		
//		return "day02/test";
//	}
}
