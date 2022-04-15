package com.member.service;

import com.member.domain.MemberVO;

// 서비스 인터페이스 : 비지니스 로직 처리 
public interface MemberService {

	// 회원 추가(가입처리) 
	public int addMember(MemberVO member); 
	// 권한 추가 
	public int addAuth(String au, String id);
	
	// 회원정보 가져오기 
	public MemberVO getMember(String id); 
	
	// 회원 정보 수정 처리 
	public int modifyMember(MemberVO member); 
	
	// 회원 탈퇴 처리 
	public int deleteMember(MemberVO member); 

	
	
	
}
