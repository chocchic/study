package com.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.domain.AuthVO;
import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;


// 서비스 구현 클래스 : 기능 구현 
@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	// 비밀번호 암호화해줄 객체 주입 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;  
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 회원 추가(가입처리) 구현 
	@Override
	public int addMember(MemberVO member) {
		// 비번 암호화 
		member.setPw(bCryptPasswordEncoder.encode(member.getPw()));
		// DB 접근해서 추가 
		int result = memberMapper.addMember(member);
		return result;
	}
	// 권한 추가 
	@Override
	public int addAuth(String au, String id) {
		int result = -1; 
		AuthVO auth = new AuthVO(); 
		auth.setId(id);
		if(au.equals("member")) {
			auth.setAuth("ROLE_MEMBER");
			result = memberMapper.addAuth(auth);
		}else if(au.equals("admin")) {
			auth.setAuth("ROLE_MEMBER");
			result = memberMapper.addAuth(auth);
			auth.setAuth("ROLE_ADMIN");
			result = memberMapper.addAuth(auth);
		}
		return result;
	}

	// 회원 정보 가져오기 
	@Override
	public MemberVO getMember(String id) { 
		MemberVO member = memberMapper.getMember(id);
		return member; // Controller한테 전달해줄게 
	}

	// 회원정보 수정 처리 구현 
	@Override
	public int modifyMember(MemberVO member) {
		int result = 0; 
		MemberVO dbMember = getMember(member.getId());
		if(bCryptPasswordEncoder.matches(member.getPw(), dbMember.getPw())) {
			result = memberMapper.updateMember(member);
		}
		
		return result; // 컨트롤러에서 갱신된 레코드수 돌려주기 
	}

	// 회원 탈퇴 처리 구현 
	@Override
	public int deleteMember(MemberVO member) {
		int result = 0; 
		MemberVO dbMember = getMember(member.getId());
		if(bCryptPasswordEncoder.matches(member.getPw(), dbMember.getPw())) {
			result = 1; 
			int deleteRes = memberMapper.deleteMember(member.getId()); 
			log.info("delete member result : " + deleteRes);  
			deleteRes = memberMapper.deleteAuth(member.getId());
			log.info("delete auth result : " + deleteRes);
		}
		
		return result; // Controller에게 id,pw 맞는지의 결과만 전달 
	}
	
	

	
	

	
}
