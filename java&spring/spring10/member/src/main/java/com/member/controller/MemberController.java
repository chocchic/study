package com.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	private MemberService memberService;
	
	// main 페이지
	/*
	@GetMapping("main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("main");
		// 쿠키 꺼내기
		Cookie[] coos = request.getCookies();
		if(coos != null) {
			for(Cookie c : coos) {
				// 쿠키가 있다!
				if(c.getName().equals("cookieAuto")) {
					// 쿠키 있다
					if(c.getName().equals("cookieAuto")) {
						int result = memberService.login(null, c.getValue(), response);
						if(result == 1) { // 쿠키에 저장된 id,pw가 맞지 않는 경우
//							return "member/login";
							break; // for문 종료해서 main으로 보내기
						}
					}
				
				}
//				if(c.getName().equals("cookiePw")) {
//					System.out.println("cookie Pw value : "+ c.getValue());
//				}
			}
		}
		// 쿠키 없다 -> 그냥 이용
		return "member/main"; // String 처리 
	}
	*/
	
	@GetMapping("main")
	public String main(@CookieValue(name="cookieAuto", required = false) String cookieAuto, HttpServletRequest request, HttpServletResponse response) {
			System.out.println("main");
			
			// 쿠키 확인해서 로그인 처리
			if(cookieAuto != null) {
				int result = memberService.login(null, cookieAuto, response);
				if(result == 1) { // 쿠키에 저장된 id,pw가 맞지 않는 경우
					return "member/login";
				}
			}
			return "member/main";
	}
	//회원가입 폼
	@GetMapping("signup")
	public void signup() {
		System.out.println("signup 폼 요청");
	}
	
	// 회원가입 처리
	@PostMapping("signup")
	public String signupPro(@ModelAttribute("member") MemberVO member, Model model) {
		System.out.println("signuppro");
		
		// 넘어온 회원정보 DB에 저장 -> 서비스야 회원가입 처리해
		int result = memberService.addMember(member);
		model.addAttribute("result",result);
		return "member/signupPro";
	}
	
	// 로그인 폼
	@GetMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("login");
		Cookie[] coos= request.getCookies();
		if(coos != null) {
			for(Cookie c : coos) {
				// no session, yes cookie
				if(c.getName().equals("cookieAuto")) {
					// 쿠키를 꺼내 로그인 처리 -> session, cookie도 갱신(쿠키 생성과정과 같음)
					memberService.login(null,c.getValue(),response);
					//createCookie();
					//return "redirect:/member/main"; //메인 요청해서 메인으로 가라
					return "/member/main"; // 쿠키 자동로그인이 되어있다면 로그인 폼대신 바로 main
				}
			}
		}
		return "member/login";
	}
//	// 로그인 처리
//	@PostMapping("login")
//	public String loginPro(@ModelAttribute("member") MemberVO member, Model model) {
//		System.out.println("loginpro");
//		
//		// 서비스에게 로그인 처리
//		int result = memberService.idpwCheck(member);
//		model.addAttribute("result",result);
//		return "member/loginPro";
//	}
	// 로그인 처리 
		@PostMapping("login")
		// 세션 꺼내기 방법#1. 컨트롤러에서 매개변수로 받아, service에 전달해주기 
		//public String loginPro(MemberVO member, Model model, HttpSession session) {
		// 세션 꺼내기 방법#2. 여기서 안꺼내고 ServiceImpl에서 바로 session 꺼내기 
		public String loginPro(MemberVO member, Model model, String auto, HttpServletResponse response) {
			System.out.println("로그인 처리 요청!!");
			System.out.println("auto : "+auto);
			// 서비스야 로그인 처리 좀 해~~ 
			//int result = memberSerivce.idPwCheck(member, session);  //방법#1.
			int result = memberService.login(member, auto, response);  // 방법#2.
			// loginPro jsp페이지에 결과 전달 
			model.addAttribute("result", result);
			
			return "member/loginPro";
		}
		
		@GetMapping("logout")
		public String logout(HttpSession session, HttpServletResponse response) {
			System.out.println("로그아웃 요청!!!!");
			// 서비스에 구현은 생략... 
			//session.invalidate(); // session에 들어있는 속성 모두 삭제 
			session.removeAttribute("memId"); // memId 속성만 삭제 
			memberService.logout(response);
			return "redirect:/member/main"; // main으로 바로 이동 
		}
		
		// 회원 정보 폼 요청
		@GetMapping("mypage")
		public void mypage() {
			System.out.println("mypage requested");
		}
		// 회원정보 수정 폼 요청
		@GetMapping("modify")
		public void modify(HttpSession session, Model model) {
			System.out.println("modify form requested");
			// 로그인한 회원 정보를 전체 뽑아서 뷰에 전달
			// MemberService가 회원정보 DB에서 가져와서 여기에 전해줌
			// 방법 1. 컨트롤러에서 ID 뽑아 서비스에 전달 
			//		세션에서 id 뽑기, Object로 돌려주니 String으로 형변환해서 담기
			//String id = (String) session.getAttribute("memId");
			//MemberVO member = memberService.getMember(id);
			MemberVO member = memberService.getMember();
			// 여기서 뷰로 전달
			model.addAttribute("member",member);
		}
		
		// 회원 정보 수정 처리 요청
		@PostMapping("modify")
		public String modifyPro(MemberVO member, Model model) { // pw, age, email
			System.out.println("modifypro requested");

			// 서비스에게 DB에서 넘어온 데이터로 수정처리 요구
			int result = memberService.modifyMember(member);
			System.out.println("Con:result modify::" + result);
			// update springMember set pw=#{pw}, age=#{age}, email=#{email}
			// where id=#{id}
			
			model.addAttribute("result",result);
			
			return "member/modifyPro";
		}
		
		// 회원 탈퇴 폼 페이지 요청
		@GetMapping("delete")
		public void delete() {
			System.out.println("delete 폼 요청");
		}
		
		// 회원 탈퇴 처리 요청
		@PostMapping("delete")
		public String deletePro(MemberVO member, Model model) { // pw만 넘어옴
			System.out.println("delete 처리 요청!!");
			
			// 서비스에게 id와 pw가 맞는지 체크해서 맞는경우 회원 삭제
			int result = memberService.deleteMember(member);
			System.out.println("C delete result : "+ result);
			
			model.addAttribute("result",result);
			
			return "member/deletePro";
		}
		
		// 아이디 중복 확인 요청
		@RequestMapping("idAvail")
		public String idAvail(String id, Model model) {
			System.out.println("id 중복요청");
			
			// id값이 DB에 존재하는지 체크해서 view 결과 전달
			int result = memberService.idAvail(id);
			model.addAttribute("trialId",id);
			model.addAttribute("result", result);
			// result == 1 : 이미 DB에 존재함, 사용불가
			// result == 0 : DB에 없다, 사용가능
			
			return "member/idAvail";
		}
	}
