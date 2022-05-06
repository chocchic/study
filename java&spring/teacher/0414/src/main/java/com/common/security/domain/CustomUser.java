package com.common.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.member.domain.MemberVO;

import lombok.Getter;

// sec#16. 
// 우리가 원하는 테이블 구조의 회원 정보를가지고 스프링시큐리티에서 적용되도록 
// MemberVO를 UserDetails 타입으로 변환시켜주는 클래스 작성. 
// UserDetails 를 구현해놓은 User 클래스를 상속받아서 만들기. -> CustomUserDetailsService에서 필요로함 

@Getter
public class CustomUser extends User{
	
	private static final long serialVersionUID = 1L;

	private MemberVO member; 
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(MemberVO vo) {
		super(vo.getId(), vo.getPw(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList())
		);
		
		this.member = vo;
		
	}
	
}
