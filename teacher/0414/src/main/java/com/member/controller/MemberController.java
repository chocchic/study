package com.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.security.domain.CustomUser;
import com.member.domain.MemberVO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member/*")
@Log4j
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("mypage")
	public void mypage() {
		log.info("mypage 요청!!!");
	}
		
	@GetMapping("modify")
	public void modify(Authentication auth, Model model) {
		log.info("modify폼 요청!!");
		CustomUser customUser = (CustomUser)auth.getPrincipal();
		log.info(customUser);
		MemberVO member = memberService.getMember(customUser.getUsername()); 
		model.addAttribute("member", member);
	}
	@PostMapping("modify")
	public String modifyPro(@AuthenticationPrincipal CustomUser customUser, MemberVO member, Model model) { // pw, age, email
		log.info("modify 처리 요청!!");
		
		member.setId(customUser.getUsername());
		int result = memberService.modifyMember(member); 
		log.info("result modify : " + result);

		model.addAttribute("result", result);
		
		return "member/modifyPro";
	}
	
	@GetMapping("delete")
	public void delete() {
		log.info("delete 폼 요청!");
	}
	
	// @AuthenticationPrincipal 어노테이션 사용을 위해서는 servlet-context.xml에 
	// annotatiion-driven > argument-resolvers > AuthenticationPrincipalArgumentResolver빈으로 추가 
	@PostMapping("delete")
	public String deletePro(@AuthenticationPrincipal CustomUser customUser, Authentication auth, MemberVO member, Model model) { // pw 만 넘어옴 
		log.info("delete 처리 요청!!");
		member.setId(customUser.getUsername());
		int result = memberService.deleteMember(member);
		log.info("delete result : " + result);
		model.addAttribute("result", result);
		
		return "member/deletePro";
	}

	
}
