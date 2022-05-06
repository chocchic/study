package com.member.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.domain.MemberVO;

// 서비스 인터페이스 : 비지니스 로직 처리 
public interface MemberService {

	// 회원 추가(가입처리) 
	public int addMember(MemberVO member); 
	// 로그인 처리 
	//public int idPwCheck(MemberVO member, HttpSession session); // 세션 방법#1.
	//public int idPwCheck(MemberVO member, String auto, HttpServletResponse response); // 세션 방법#2.
	
	// 통합 로그인
	public int login(MemberVO member, String auto, HttpServletResponse response);
	
	// 회원정보 가져오기 
	//public MemberVO getMember(String id); // #1. 컨트롤러에서 id 뽑아 던져주기   
	public MemberVO getMember();  	  // #2. 서비스에서 직접 id 뽑아 사용 
	
	// 회원 정보 수정 처리 
	public int modifyMember(MemberVO member); 
	
	// 회원 탈퇴 처리 
	public int deleteMember(MemberVO member, HttpServletResponse response);

	// 로그아웃 
	public void logout(HttpServletResponse response); 
	
	// 아이디 중복 확인 
	public int idAvail(String id);
	
	
}
