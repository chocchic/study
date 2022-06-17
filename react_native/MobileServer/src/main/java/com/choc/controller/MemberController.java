package com.choc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choc.dto.MemberDTO;
import com.choc.dto.ResponseMemberDTO;
import com.choc.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			// 데이터 삽입 처리
			String email = memberService.registerMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
}
