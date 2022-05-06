package com.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;


// 서비스 구현 클래스 : 기능 구현 
@Service
public class MemberServiceImpl2 implements MemberService2 {

	@Autowired
	private MemberMapper memberMapper;
	
	// 회원 추가(가입처리) 구현 
	@Override
	public int addMember(MemberVO member) {
		// DB 접근해서 추가 
		int result = memberMapper.addMember(member);
		return result;
	}


	// 회원 정보 가져오기 
	@Override
	public MemberVO getMember() { 
		String id = (String)(getSession().getAttribute("memId")); 
		MemberVO member = memberMapper.getMember(id);
		return member; // Controller한테 전달해줄게 
	}

	// 회원정보 수정 처리 구현 
	@Override
	public int modifyMember(MemberVO member) {
		
		String id = (String)(getSession().getAttribute("memId")); 
		member.setId(id);  // member에 id값도 체워서 mapper에 보내기 
		
		int result = memberMapper.updateMember(member);
		
		return result; // 컨트롤러에서 갱신된 레코드수 돌려주기 
	}

	// 회원 탈퇴 처리 구현 
	@Override
	public int deleteMember(MemberVO member, HttpServletResponse response) {

		HttpSession session = getSession(); 
		String id = (String)session.getAttribute("memId"); 
		member.setId(id);  // member에 id값도 체워서 mapper에 보내기 
		
		// id, pw 체크 
		int result = memberMapper.idPwCheck(member); 
		
		if(result == 1) {  // 맞으면 delete
			// DB 삭제 처리 
			int deleteRes = memberMapper.deleteMember(member.getId()); 
			System.out.println("S delete result : " + deleteRes); // 개발자만 확인 
			// 로그아웃 
			logout(response);
			
		}
		// 안맞으면 아무것도 안함 
		
		return result; // Controller에게 id,pw 맞는지의 결과만 전달 
	}
	
	// 세션가져오기 (메서드로 중복코드 분리) 
	private HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		HttpSession session = request.getSession(); 
		return session;
	}

	// 통합 로그인 
	@Override
	public int login(MemberVO member, String auto, HttpServletResponse response) {
		System.out.println("login service, member : " + member);
		System.out.println("login service, auto : " + auto);
		// member가 안들어올경우 객체 생성만 해주고 
		if(member == null) {
			member = new MemberVO();
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Cookie[] coos = request.getCookies(); 
		if(coos != null) {
			for(Cookie c : coos) {
				if(c.getName().equals("cookieId")) member.setId(c.getValue());
				if(c.getName().equals("cookiePw")) member.setPw(c.getValue());
				if(c.getName().equals("cookieAuto")) auto = c.getValue();
			}
		}
		// id, pw 일치하는지 DB가서 확인 
		//if(member.getId() != null && member.getPw() != null )
		int result = memberMapper.idPwCheck(member);
		HttpSession session = getSession(); 
		
		// 일치하면 세션만들고, 쿠키 원하면 만들기 
		if(result == 1) {
			if(session.getAttribute("memId") == null) {
				session.setAttribute("memId", member.getId());
			}
			if(auto != null) { // 자동로그인체크, 또는 쿠키에 자동로그인 있으면
				Cookie c1 = new Cookie("cookieId", member.getId()); 
				Cookie c2 = new Cookie("cookiePw", member.getPw()); 
				Cookie c3 = new Cookie("cookieAuto", auto); 
				c1.setMaxAge(60*60*24); // 24시간 
				c2.setMaxAge(60*60*24); // 24시간 
				c3.setMaxAge(60*60*24); // 24시간 
				c1.setPath("/");
				c2.setPath("/");
				c3.setPath("/");
				response.addCookie(c1);
				response.addCookie(c2);
				response.addCookie(c3);
			}
		}
		return result; // id,pw 체크한 결과 리턴 
	}

	// 로그아웃 
	@Override
	public void logout(HttpServletResponse response) {
		// 세션 삭제 
		getSession().invalidate();
		// 쿠키 삭제 
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Cookie[] coos = request.getCookies(); 
		if(coos != null) {
			for(Cookie c : coos) {
				if(c.getName().equals("cookieId") || c.getName().equals("cookiePw") || c.getName().equals("cookieAuto")) {
					c.setPath("/");
					c.setMaxAge(0);
					response.addCookie(c);
					System.out.println(c.getMaxAge());
				}
			}
		}
		
	}
	

	
	
	
	

	
	
	
	
	

	
}
