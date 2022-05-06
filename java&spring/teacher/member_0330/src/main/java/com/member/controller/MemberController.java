package com.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	// 회원가입 폼 
	@GetMapping("signup")
	public void signup() {
		System.out.println("signup 폼 요청!!!");
	}
	// 회원가입 처리 
	@PostMapping("signup")
	public String signupPro(@ModelAttribute("member") MemberVO member, Model model) {
		System.out.println("signup 처리 요청!!!");
		
		int result = memberService.addMember(member);
		model.addAttribute("result", result);
		
		return "member/signupPro";
	}
	
	/* 메인페이지 
	@GetMapping("main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("main 요청");
		
		// 쿠키 꺼내기 
		Cookie[] coos = request.getCookies(); 
		if(coos != null) {
			for(Cookie c : coos) {
				// 쿠키 있다 
				if(c.getName().equals("cookieAuto")) {
					int result = memberService.login(null, c.getValue(), response);
					if(result == 0) { // 쿠키에 저장된 id,pw가 맞지 않을경우 
						break; // for문 종료해서 main으로 그냥 이동하게 
					}
				}
			}
		}
		// 쿠키 없다 -> 그냥 이동 
		return "member/main"; // String 처리 
	} */
	
	@GetMapping("main")
	public String main(@CookieValue(name="cookieAuto", required = false) String cookieAuto, HttpServletResponse response) {
		System.out.println("main 요청");
		
		// 쿠키 확인해서 로그인처리  
		if(cookieAuto != null) {
			memberService.login(null, cookieAuto, response);
		}
		// 쿠키 없다 -> 그냥 이동 
		return "member/main"; // String 처리 
	}
	
	// 로그인 폼 
	@GetMapping("login")
	public String login(@CookieValue(name="cookieAuto", required = false) String cookieAuto, HttpServletResponse response) {
		System.out.println("로그인 폼 요청!!!");
		if(cookieAuto != null) {
			// 쿠키 꺼내서 로그인 처리 -> session, cookie(갱신=쿠키생성) 
			int result = memberService.login(null, cookieAuto, response);
			if(result == 1) {
				return "member/main"; // 쿠키로 자동로그인되었으니 로그인폼대신 메인페이지 보여줘 
			}
		}
		return "member/login";  // 원래 요청한 login 폼페이지 보여줘 
	}
	
	// 로그인 처리 
	@PostMapping("login")
	public String loginPro(MemberVO member, String auto, Model model, HttpServletResponse response) {
		System.out.println("로그인 처리 요청!!");
		
		System.out.println("auto : " + auto);
		
		int result = memberService.login(member, auto, response);
		model.addAttribute("result", result);
		
		return "member/loginPro";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		System.out.println("로그아웃 요청!!!!");
		
		memberService.logout(response);
		
		return "redirect:/member/main"; // main으로 바로 이동 
	}
	
	// 마이페이지 
	@GetMapping("mypage")
	public void mypage() {
		System.out.println("mypage 요청!!!");
	}
	
	// 회원 정보 수정 폼 요청 
	@GetMapping("modify")
	public void modify(HttpSession session, Model model) {
		System.out.println("modify폼 요청!!");
		MemberVO member = memberService.getMember(); 
		// 내가 받아서 뷰에 전달할게 
		model.addAttribute("member", member);
	}
	
	// 회원 정보 수정 처리 요청 
	@PostMapping("modify")
	public String modifyPro(MemberVO member, Model model) { // pw, age, email
		System.out.println("modify 처리 요청~~");
		
		int result = memberService.modifyMember(member); 
		System.out.println("C - result modify : " + result);
		model.addAttribute("result", result);
		
		return "member/modifyPro";
	}
	
	
	// 회원 탈퇴 폼 페이지 요청 
	@GetMapping("delete")
	public void delete() {
		System.out.println("delete 폼 요청!");
	}
	
	// 회원 탈퇴 처리 요청 
	@PostMapping("delete")
	public String deletePro(MemberVO member, Model model, HttpServletResponse response) { // pw 만 넘어옴 
		System.out.println("delete 처리 요청!!");
		
		int result = memberService.deleteMember(member, response);
		System.out.println("C delete result : " + result);
		model.addAttribute("result", result);
		
		return "member/deletePro";
	}
	
	// 아이디 중복 확인 요청 
	@RequestMapping("idAvail")
	public String idAvail(String id, Model model) {
		System.out.println("id 중복확인 요청");
		
		// id값이 DB에 존재하는지 체크해서 view 결과 전달 
		int result = memberService.idAvail(id); 
		model.addAttribute("result", result);
		model.addAttribute("trialId", id);
		// result == 1 : 이미 DB에 존재함, 사용불가 
		// result == 0 : DB에 없다, 사용가능 
		
		return "member/idAvail";
	}
	
	/*
	@RequestMapping("ajaxIdAvail")
	@ResponseBody	// 응답을 jsp페이지가아닌, body(ajax요청한곳)으로 응답하겠다~ 
	public String ajaxIdAvail(String id) {
		System.out.println("ajaxIdAvail 요청 : " + id);
		int result = memberService.idAvail(id); 
		String resStr = ""; 
		if(result == 1) {
			resStr = "이미 존재합니다"; 
		}else {
			resStr = "사용 가능합니다"; 
		}
		
		return resStr;  // jsp페이지가아닌 body로 응답해줄 데이터 전송 (데이터가 문자열이라 리턴타입도 String) 
	}*/
	
	// 응답 데이터의 한글깨짐 방지 
	@RequestMapping("ajaxIdAvail")
	public ResponseEntity<String> ajaxIdAvail(String id) {
		System.out.println("ajaxIdAvail 요청 : " + id);
		int result = memberService.idAvail(id); 
		String resStr = ""; 
		if(result == 1) {
			resStr = "이미 존재합니다"; 
		}else {
			resStr = "사용 가능합니다"; 
		}
		
		// 헤더정보 세팅  
		HttpHeaders responseHeader = new HttpHeaders(); 
		responseHeader.add("Content-Type", "text/html;charset=utf-8"); // 한글 깨짐 방지 헤더 정보 
		
		return new ResponseEntity<String>(resStr, responseHeader, HttpStatus.OK);  
		// body에 전송할데이터, 헤더객체, http상태코드 (ok = 200, created = 201)
	}
	
	
	
	
	
	
	
	
}
