package com.member.service;

import javax.servlet.http.HttpSession;

import com.member.domain.MemberVO;

// 서비스 인터페이스 : 비지니스 로직 처리 
public interface MemberService {

	// 회원 추가(가입처리) 
	public int addMember(MemberVO member); 
	// 로그인 처리 
	//public int idPwCheck(MemberVO member, HttpSession session); // 세션 방법#1.
	public int idPwCheck(MemberVO member); // 세션 방법#2.
	
}
