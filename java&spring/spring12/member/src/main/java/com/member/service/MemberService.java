package com.member.service;

import javax.servlet.http.HttpServletResponse;

import com.member.domain.MemberVO;

// 서비스 인터페이스 : 비즈니스 로직 처리
public interface MemberService {
	
	// 회원 추가(회원 가입)
	public int addMember(MemberVO member);
	
	// 로그인 처리
	//public int idpwCheck(MemberVO member, HttpSession session); // 세션 방법#1.
	public int idpwCheck(MemberVO member, String auto, HttpServletResponse response); // 세션 방법#2.
	
	// 로그인이라는 method 추가
	public int login(MemberVO member, String auto, HttpServletResponse response);
	
	// 회원 한명 정보 가져오기
	//public MemberVO getMember(String id); // 방법 1. 컨트롤러에서 id 뽑아 던져주기
	public MemberVO getMember(); // 방법 2. 서비스에서 직접 id 뽑아 사용
	
	// 회원 정보 수정 처리
	public int modifyMember(MemberVO member);
	
	// 회원 삭제
	public int deleteMember(MemberVO member);
	
	// 로그아웃
	public void logout(HttpServletResponse response);
	// session 생성
	public void createSession(String id);
	
	// cookie 생성 (갱신할때도 사용)
	public void createCookie();
	
	public int idAvail(String id);
}
