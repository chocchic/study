package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.service.KakaoPayService;

@Controller
@RequestMapping("/kakao/*")
public class KakaoController {
	@Autowired
	private KakaoPayService service;
	
	@GetMapping("kakaoPay")
	public void kakaoPayGet() {
		System.out.println("kakaoPay Get 요청");
	}
	
	@PostMapping("kakaoPay")
	public String kakaoPay() {
		System.out.println("kakaoPay Post 요청");
		// 자바를 통해서 kakao api에서 제공한 주소로 http 요청
		String responseURL = service.kakaoPayReady();
		// 서비스 처리 결과중 카카오 페이지 준비 결과에 따른 이동할 페이지 주소로 redirect해주기
		// 위에서 리턴받은 페이지 주소로 redirect:
		return "redirect:" + responseURL ; // 주소연결
		
	}
	// 준비 요청성공시 실행될 요청 메서드
	// 파라미터로 넘어오는 pg_token 받고
	@GetMapping("kakaoPaySuccess")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		System.out.println("kakaoPaySuccess.......");
		System.out.println("pg_token : "+ pg_token);
		// 결제 승인 요청(pg_token 전달하면서)
		model.addAttribute("payInfo", service.kakaoPayApprove(pg_token));
	}
}
