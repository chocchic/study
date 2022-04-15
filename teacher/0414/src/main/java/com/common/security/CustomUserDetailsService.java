package com.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.common.security.domain.CustomUser;
import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

// sec#14. 우리가 원하는 데이터베이스 구조에 맞게 스프링시큐리티가 실행되기 위해서 
//		사용자 정보와 권한 처리하는 UserDetailService 인터페이스를 직접 구현
//		이때, 오버라이딩 해야하는 loadUserByUsername 메서드의 리턴타입은 UserDetails(회원정보담은 객체) 이므로 
//		MemberVO를 해당타입으로 변환해주는 CustomerUser 타입 클래스를 만들고 
//		mapper에서 회원정보와 권한정보를 뽑아 CustomerUser를 통해 MemberVO를 UserDetails 타입 객체로 변환하여 리턴해준다. 
@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		log.warn("Load User By UserName : " + userName);

		// MemberVO를 CustomUser로 리턴하도록 지정 
		MemberVO vo = memberMapper.getMember(userName); // id를 주고 DB에서 사용자정보와 권한을 추출해 
		log.warn("queried by member mapper : " + vo);
		return vo == null ? null : new CustomUser(vo); // UserDetails를 구현해놓은 CustomerUser 타입으로 변환해 리턴 
		
	}

	
	
	
	
}
