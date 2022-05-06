package com.member.mapper;

import com.member.domain.AuthVO;
import com.member.domain.MemberVO;

// 회원 가입 mapper
public interface MemberMapper {

	//회원 추가 
	public int addMember(MemberVO member);
	//권한 추가 
	public int addAuth(AuthVO auth);
	
	// 회원 한명 정보 가져오기 
	public MemberVO getMember(String id); 
	
	// 회원 정보 수정 
	public int updateMember(MemberVO member);
	
	// 회원 삭제 
	public int deleteMember(String id);
	// 권한 삭제 
	public int deleteAuth(String id);
	
	
	
	
}
