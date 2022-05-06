package com.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/common/*")
@Log4j
public class CommonController {

	@Autowired
	private MemberService memberService; 
	
	@GetMapping("main")
	public void main() {
		log.info("main!!");
	}

	@GetMapping("login")
	public void login(String error, String logout, Model model, HttpServletRequest request) {
		log.info("login form!!!");
		log.info("error : " + error);
		log.info("logout : " + logout);
		
		String referrer = request.getHeader("Referer"); 
		request.getSession().setAttribute("prevPage", referrer);
		if(error != null) {
			model.addAttribute("error", "Login Error! Check Your Acccount"); 
		}
		if(logout != null) {
			model.addAttribute("logout", "Logout!!"); 
		}
	}
	
	// 회원가입 폼 
	@GetMapping("signup")
	public void signup() {
		log.info("signup form!!!");
	}
	// 회원가입 처리 
	@PostMapping("signup")
	public String signupPro(String au, MemberVO vo, RedirectAttributes rttr) {
		log.info("signup Pro!!!!");
		log.info("au : " + au);
		
		int result = memberService.addMember(vo); 
		memberService.addAuth(au, vo.getId());
		
		if(result == 1) {
			rttr.addFlashAttribute("msg", "success"); 
		}
		
		return "redirect:/common/main";
	}
	
	// 접근 제한시 처리할 경로 
	@GetMapping("accessError")
	public void accessDenied(Authentication auth, Model model) {
		// 사용자 정보가 필요한경우 Authentication 타입 매개변수 추가 
		log.info("accessDenied : " + auth); 
		
		model.addAttribute("msg", "access denied"); 
	}
	
	
	
	
}
