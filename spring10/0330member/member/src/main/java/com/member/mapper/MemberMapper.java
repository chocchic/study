package com.member.mapper;

import javax.servlet.http.HttpServletResponse;

import com.member.domain.MemberVO;

// 회원 가입 mapper
public interface MemberMapper {
	//회원 추가
	public int addMember(MemberVO member);
	
	//로그인 처리(id,pw 확인)
	public int idpwCheck(MemberVO member);
	
	//회원 한명 정보 가져오기
	public MemberVO getMember(String id);
	
	// 회원 정보 수정
	public int updateMember(MemberVO member);

	public int deleteMember(String id);
	// 아이디 중복확인 쿼리
	public int idCount(String id);
}
