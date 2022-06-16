package com.choc.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.choc.dto.MemberDTO;
import com.choc.model.Member;

@Service
public interface MemberService {
	// 데이터 삽입
	public String registerMember(MemberDTO dto);
	public MemberDTO loginMember(MemberDTO dto);
	public MemberDTO getMember(MemberDTO dto);
	public String updateMember(MemberDTO dto);
	public String deleteMember(MemberDTO dto);
	
	// DTO클래스의 객체를 Model클래스의 객체로 변환
	public default Member dtoToEntity(MemberDTO dto) {
		String password = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		Member member = Member.builder().email(dto.getEmail()).name(dto.getName()).password(password)
				.imageurl(dto.getImageurl()).lastlogindate(dto.getLastlogindate()).build();
		
		return member;
	}
	
	// Model클래스의 객체를 DTO클래스의 객체로 변환
	public default MemberDTO entityToDto(Member member) {
		MemberDTO dto = MemberDTO.builder().email(member.getEmail()).name(member.getName()).imageurl(member.getImageurl())
				.regdate(member.getRegDate()).moddate(member.getModDate()).lastlogindate(member.getLastlogindate()).build();
		return dto;
	}
}
