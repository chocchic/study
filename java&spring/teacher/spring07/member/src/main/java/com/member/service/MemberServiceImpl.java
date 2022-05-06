package com.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;

// 서비스 구현 클래스 : 기능 구현 
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	// 회원 추가(가입처리) 구현 
	@Override
	public int addMember(MemberVO member) {
		// DB 접근해서 추가 
		int result = memberMapper.addMember(member);
		return result;
	}

	// 로그인 처리 
	@Override
	//public int idPwCheck(MemberVO member, HttpSession session) { // 세션방법#1.
	public int idPwCheck(MemberVO member) { // 세션 방법#2.
		int result = memberMapper.idPwCheck(member);
		System.out.println("result service :" + result);
		//방법#2. 여기서 session 바로 꺼내기 
		// 풀어쓰기 
		//ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		//HttpServletRequest request = sra.getRequest();  // request 객체 (요청관련정보담은아이) 
		
		// 두줄로 줄이기 
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  // request 객체 (요청관련정보담은아이) 
		//HttpSession session = request.getSession(); 
		
		// 한줄로 줄이기
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession(); 
		
		
		// result 결과를 확인하고 1 = 로그인 = session에 속성추가 
		if(result == 1) {
			// session에 memId라는 이름으로 사용자 id 추가 --> 로그인확인용으로 사용할것임 
			session.setAttribute("memId", member.getId()); 
		}
		// 0 = 로그인실패 
		
		return result;
	}
	
	

	
}
