package com.test.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.sample.Chef;

@Controller
@RequestMapping("/day02/")
public class TestController {

	//private Date day = new Date(); // 이제는 이렇게 new로 객체생성 안함. 
	
	// 의존성 자동 주입 : 변수위에 @Autowired 붙혀주면 
	//	xml파일에 <bean>으로 등록된 스프링빈(생성된객체)를 찾아서 자동으로 주입해준다. 
	// 객체생성은 -context.xml 파일에 bean으로 등록하고 필요한 곳에서 주입받아 사용가능. 
	// 먼저 변수의 타입먼저 확인해서 주입해줌. 
	@Autowired // 각각 변수에 하나씩 붙혀주기 
	private Date date; 
	@Autowired
	private Date day; 
	
	@Autowired
	private Chef chef;
	
	// #1. 리턴타입 : String : return "jsp파일이름"  
	@GetMapping("hello")
	public String hello() { 
		System.out.println("/test/hello 요청됨!!!");
		return "day02/test"; 
	}
	
	// #2. 리턴타입 : void : 요청경로 == jsp 파일 경로 
	@GetMapping("hello2")
	public void hello2() {
		System.out.println("/day02/hello2 요청!!");
		//day = new Date(); 
		System.out.println("Date : " + date);
		System.out.println("Date2 : " + day);
		System.out.println("chef : " + chef);
		
	}
	// http://localhost:8080/day02/hello2
	
	
	
	
	
	
	
	
	
	
	
	
	
}
