package com.test.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.model.TestDTO;

@Controller
@RequestMapping("/day03/")
public class TestController2 {
	@GetMapping("test01")
	public void test01(String a) {
		
		System.out.println("/day03/test01 호출 !!");
		System.out.println(a);
	}
	// form페이지 요청
	@GetMapping("form")
	public void form() {
		System.out.println("form requested!!");
	}
	
	// 폼에서 submit 눌러서 요청되는 pro요청 매핑
	//http://localhost:8080/day03/pro?id=java&pw=1234
	@GetMapping("pro")
//	# 1. 매개변수 나열해서 하나씩 따로 받기
/*
	public void pro(String id, String pw){
		System.out.print("id : " + id);
		System.out.println(", pw : " + pw);
		// TestDTO를 만들어서 거기에 직접 담아주기 
		TestDTO dto = new TestDTO();
		dto.setId(id);
		dto.setPw(pw);
	}
*/
//	# 2. DTO 안에 자동으로 바인딩해서 받기 : 이때 바인딩 되는 객체(DTO) 안에는 setter 존재해야 함
	// get으로 보내진 변수에 대해서 따로 담아진다!
/*	public void pro(TestDTO dto, String id, int pw) {
		System.out.print("String id : " + id);
		System.out.println(", int pw : " + pw);
		System.out.println(dto);
		System.out.print("id : " + dto.getId());
		System.out.println(", pw : " + dto.getPw());
}
*/

	public void pro(TestDTO dto, HttpServletRequest request, HttpSession session) {
		System.out.println("pro requested!");
		System.out.println("request : " + request);
		System.out.println("request : " + request.getRequestURI());
		System.out.println(dto);
		System.out.print("id : " + dto.getId());
		System.out.println(", pw : " + dto.getPw());
	}
	// ...8080/day03/ex01?name=abc&age=10
	// @Requestparam("파라미터명")
//			: 요청할 떄 넘어온 파라미터를 명시적으로 매개변수 앞에 붙혀서 해당 파라미터는 이 매개변수에 담아야 된다고 알려주는 것
//			  어노테이션을 붙히면 매개변수 이름은 파라미터 이름과 달라도 괜찮다
	@GetMapping("ex01")
	public void ex01(@RequestParam("name")String myName, @RequestParam("age")int myAge) {
		System.out.println("ex01 requested");
		System.out.println("name : " + myName);
		System.out.println("age : " + myAge);
	}
	
	// 파라미터 ArrayList에 담기
	// ...8080/day03/ex02??arr=a&arr=b&arr=c
	@GetMapping("ex02")
//	public void ex02(@RequestParam("arr") ArrayList<String> arr) {
	public void ex02(String[] arr) {
		System.out.println("ex02 requested!!");
		System.out.println(arr);
		for(String s : arr) {
			System.out.println(s);
		}
	}
	
	// 뷰에 데이터 보내기
	/*
	@GetMapping("ex03")
	public void ex03(HttpServletRequest request) {
		System.out.println("ex03 요청~~!!");
		
		//request 객체에 속성 추가
		request.setAttribute("name", "pppppie");
		request.setAttribute("name2", "cola");
	}
	*/
	@GetMapping("ex03")
	public void ex03(HttpServletRequest request, Model model) {
		System.out.println("ex03 요청~~!!");
		
		//model 객체에 속성 추가
		model.addAttribute("name","ppie");
		model.addAttribute("name2","coka cola");
	}
}
