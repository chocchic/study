package com.member.mapper;

import com.member.domain.MemberVO;

// 회원 가입 mapper
public interface MemberMapper {

	//회원 추가 
	public int addMember(MemberVO member);
	
	//로그인처리 (id,pw 확인) 
	public int idPwCheck(MemberVO member);
	
	// 회원 한명 정보 가져오기 
	public MemberVO getMember(String id); 
	
	// 회원 정보 수정 
	public int updateMember(MemberVO member);
	
	// 회원 삭제 
	public int deleteMember(String id);
	
	// 아이디 중복 확인 쿼리 
	public int idCount(String id);
	
	
	
}
