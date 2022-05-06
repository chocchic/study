package com.member.mapper;

import com.member.domain.MemberVO;

// 회원 가입 mapper
public interface MemberMapper {

	//회원 추가 
	public int addMember(MemberVO member);
	
	//로그인처리 (id,pw 확인) 
	public int idPwCheck(MemberVO member);
	
	
}
