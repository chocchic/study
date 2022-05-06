package com.test.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.TestDTO;
import com.test.model.TestTV;

@Controller
@RequestMapping("/day04/")
public class TestController3 {

	@GetMapping("test01")
	public void test01(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println("test01");
		
		// Model : Map의 구조로 생김. key, value 형태로 속성 추가 가능
		//		   addAttribute(String key, Object value);
		model.addAttribute("name","고니");
		model.addAttribute("age",20);
		
		// 요청당 하나
		//request.setAttribute("name","정마담");
//		request.setAttribute("age", 1000);
		
		// 브라우저당 하나
		session.setAttribute("name", "아귀");
		session.setAttribute("age", 50);
		
		model.addAttribute("arr",new int[] {1,2,3,4,5});
	}
	
	@GetMapping("test02")
	public void test02(Model model) {
		System.out.println("test02");
		model.addAttribute("dto", new TestDTO());
		model.addAttribute("arr", new int[] {1,2,3,4,5});
	}
	
	@GetMapping("test03")
	public void test03(Model model) {
		System.out.println("test03");
		model.addAttribute("day",new Date());
		model.addAttribute("arr",new int[] {10,20,30,40,50,60}); // 배욜
		List list = new ArrayList();
		for(int i=0;i<5;i++) {list.add(i+1);} // list.get(인덱스)로 값 꺼내기
		model.addAttribute("list",list); // arraylist
		
		TestDTO dto = new TestDTO();
		dto.setAge(2000);
		dto.setId("pikachu");
		model.addAttribute("dto",dto); // TestDTO객체
	}
	
	// http://localhost:8080/day04/test04?id=java&pw=1111
	@GetMapping("test04")
	public void test04(@ModelAttribute("dto") TestDTO dto, @ModelAttribute("id") String id, @ModelAttribute("pw") int pw, Model model) {
		// 그대로 보내주면 된다면 매개변수에 어노테이션을 이용해서 보내주고
		model.addAttribute("newid",id+"hello"); //가공이 필요할 때만 이런 식으로 보내준다.
		
	}
	
	//메서드 레벨에 어노테이션 부착 : test01~test04 모든 view에서 tv객체 사용 가능
	// 이 클래스에 있는 요청 메서드 호출 전에 먼저 아래 메서드가 호출되면서 뷰에 tv객체 전달
	// -> 이 클래스에 모든 jsp에서 필요한 객체가 있으면 아래와 같이 작성해주기
	@ModelAttribute("tv")
	public TestTV getTv(String color) {
		System.out.println("getTV 호출!!");
		TestTV tv = new  TestTV();
		tv.setPower(true);
		tv.setCh(10);
		tv.setVol(5);
		tv.setColor(color);
		return tv;
	}
	/*
	@GetMapping("test05")
//	public void test05() // url경로가 jsp 파일 경로와 동일
	public String test05(Model model) {
		model.addAttribute("hello","hihihihihihi");
		return "day04/test05"; // jsp 파일 경로 리턴
	}*/
	// 요즘은 잘 안쓰는 방식
	/*
	@GetMapping("test05")
	public ModelAndView test05() {
		// 객체 생성
		ModelAndView mv = new ModelAndView();
		// 뷰에 전달할 데이터 추가
		mv.addObject("hello","hahahaha"); // model.addAttribute()
		// 뷰 경로 저장
		mv.setViewName("day04/test05");
		return mv;
	}
	*/
	/*
	@GetMapping("test05")
	public String test05() {
		System.out.println("test05 requested");
		// 스프링에서 기본적으로 forward방식으로 이동
		return "redirect:/day04/testNewpage"; // 이동이라는 요청을 내리는 거임!!
	}
	@GetMapping("testNewpage")
	public void testNewpage() {
		System.out.println("testNewpage requested");
	}
	*/
	@PostMapping("test05")
	public void test05() {
		System.out.println("test05 requested");
	}
}
