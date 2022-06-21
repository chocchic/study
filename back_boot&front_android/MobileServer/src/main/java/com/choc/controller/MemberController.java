package com.choc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.choc.service.MemberService;
import com.choc.dto.*;
@RestController
@RequestMapping("member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//데이터 삽입 처리
			String email = memberService.registerMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//로그인 처리
			MemberDTO result = memberService.loginMember(dto);
			if(result == null) {
				response = ResponseMemberDTO.builder()
						.error("없는 이메일이나 잘못된 비밀번호입니다.").build();
			}else {
				response = ResponseMemberDTO.builder()
						.email(result.getEmail())
						.name(result.getName())
						.imageurl(result.getImageurl())
						.regdate(result.getRegdate())
						.moddate(result.getModdate())
						.lastlogindate(result.getLastlogindate())
						.build();
			}
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> getMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//회원 정보 가져오기
			MemberDTO result = memberService.getMember(dto);
			if(result == null) {
				response = ResponseMemberDTO.builder()
						.error("없는 이메일입니다.").build();
			}else {
				response = ResponseMemberDTO.builder()
						.email(result.getEmail())
						.name(result.getName())
						.imageurl(result.getImageurl())
						.regdate(result.getRegdate())
						.moddate(result.getModdate())
						.lastlogindate(result.getLastlogindate())
						.build();
			}
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//데이터 수정 처리
			String email = memberService.updateMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> deleteMember(MemberDTO dto){
		ResponseMemberDTO response = null;
		try {
			//데이터 삭제 처리
			String email = memberService.deleteMember(dto);
			response = ResponseMemberDTO.builder().email(email).build();
		}catch(Exception e) {
			String error = e.getMessage();
			response = ResponseMemberDTO.builder().error(error).build();
		}
		return ResponseEntity.ok().body(response);
	}
	
	//안드로이드에서는 서버의 이미지를 다운로드 받아야 출력할 수 있기 때문에 이미지를 다운로드
	//받을 수 있는 요청을 생성해야 합니다.
	@GetMapping("/download")
	public ResponseEntity<Object> download(String path){
		return memberService.download(path);
	}
}