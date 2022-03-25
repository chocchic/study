package com.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberService memberSerivce;
	
	// 회원가입 폼 
	@GetMapping("signup")
	public void signup() {
		System.out.println("signup 폼 요청!!!");
	}
	// 회원가입 처리 
	@PostMapping("signup")
	public String signupPro(@ModelAttribute("member") MemberVO member, Model model) {
		System.out.println("signup 처리 요청!!!");
		
		// 넘어온 회원정보 DB 저장 -> 서비스야 회원가입처리일해~~ 
		int result = memberSerivce.addMember(member);
		// 처리 결과 화면까지 보내줘~ 
		model.addAttribute("result", result);
		
		return "member/signupPro";
	}
	
	// 메인페이지 
	@GetMapping("main")
	public void main() {
		System.out.println("main 요청");
	}
	
	// 로그인 폼 
	@GetMapping("login")
	public void login() {
		System.out.println("로그인 폼 요청!!!");
	}
	
	// 로그인 처리 
	@PostMapping("login")
	// 세션 꺼내기 방법#1. 컨트롤러에서 매개변수로 받아, service에 전달해주기 
	//public String loginPro(MemberVO member, Model model, HttpSession session) {
	// 세션 꺼내기 방법#2. 여기서 안꺼내고 ServiceImpl에서 바로 session 꺼내기 
	public String loginPro(MemberVO member, Model model) {
		System.out.println("로그인 처리 요청!!");
		
		// 서비스야 로그인 처리 좀 해~~ 
		//int result = memberSerivce.idPwCheck(member, session);  //방법#1.
		int result = memberSerivce.idPwCheck(member);  // 방법#2.
		// loginPro jsp페이지에 결과 전달 
		model.addAttribute("result", result);
		
		return "member/loginPro";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("로그아웃 요청!!!!");
		// 서비스에 구현은 생략... 
		//session.invalidate(); // session에 들어있는 속성 모두 삭제 
		session.removeAttribute("memId"); // memId 속성만 삭제 

		return "redirect:/member/main"; // main으로 바로 이동 
	}
	
	
	
	
	
	
	
	
	
	
	
}
