package com.member.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.member.domain.MemberVO;
import com.member.mapper.MemberMapper;

// 서비스 구현 클래스 : 기능 구현
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberMapper memberMapper;
	
	// 회원 추가 (가입 처리) 구현
	@Override
	public int addMember(MemberVO member) {
		
		// DB 접근해서 추가
		int result = memberMapper.addMember(member);
		
		return result;
	}
//	간단한 로그인 확인
//	@Override
//	public int idpwCheck(MemberVO member) {
//		int result = memberMapper.idpwCheck(member);
//		
//		return result;
//	}
	
	// 로그인 처리 
	@Override
	//public int idPwCheck(MemberVO member, HttpSession session) { // 세션방법#1.
	public int idpwCheck(MemberVO member, String auto, HttpServletResponse response) { // 세션 방법#2.
		int result = memberMapper.idpwCheck(member);
		System.out.println("result service :" + result);			//방법#2. 여기서 session 바로 꺼내기 
		// 풀어쓰기 
		//ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		//HttpServletRequest request = sra.getRequest();  // request 객체 (요청관련정보담은아이) 
			
		// 두줄로 줄이기 
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  // request 객체 (요청관련정보담은아이) 
		//HttpSession session = request.getSession(); 
		
		// 한줄로 줄이기
		//HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession(); 
		
		// result 결과를 확인하고 1 = 로그인 = session에 속성추가 
		if(result == 1) {
		// session에 memId라는 이름으로 사용자 id 추가 --> 로그인확인용으로 사용할것임 
			getSession().setAttribute("memId", member.getId()); 
			if(auto!=null) {
				Cookie c1 = new Cookie("cookieId", member.getId()); 
				Cookie c2 = new Cookie("cookieId", member.getPw());
				c1.setMaxAge(60); // 24시간
				c2.setMaxAge(60); // 24시간
				c1.setPath("/");
				c2.setPath("/");
				response.addCookie(c1);
				response.addCookie(c2);
			}
		}
		// 0 = 로그인실패 
		
		return result;
	}
	
	// 회원정보 가져오기
	@Override
//		public MemberVO getMember(String id) { // 방법 1
	public MemberVO getMember() {	// 방법 2.
		// 방법 2.
		
		String id = (String)getSession().getAttribute("memId");
		// 방법 1,2
		 MemberVO member = memberMapper.getMember(id);
	      
	     return member; // Controller에게 전달 
	}
	
	// 회원정보 수정 처리 구현	
	@Override
	public int modifyMember(MemberVO member) {
		String id = (String)getSession().getAttribute("memId");
		member.setId(id); // member에 id값 채워서 mapper에 보내기
		
		int result = memberMapper.updateMember(member);
		
		return result;
	}
	
	// 회원 탈퇴 처리 구현
	@Override
	public int deleteMember(MemberVO member) {
		HttpSession session = getSession();
		String id = (String)session.getAttribute("memId");
		member.setId(id);
		// id, pw 체크
		int result = memberMapper.idpwCheck(member);
		
		// 위에서 맞았을 떄만 delete
		if(result == 1) {
			int deleteR = memberMapper.deleteMember(member.getId());
			System.out.println("S delete Result : " + deleteR); // 개발자만 확인
			// 로그아웃
			session.invalidate();
		}
		// 안맞으면 아무것도 안함
			
		return result; // controller에게 id,pw가 맞는지의 결과 전달
	}
	
	// login method
	@Override
	public int login(MemberVO member, String auto, HttpServletResponse response) {
		// member가 안 들어올 경우 객체 생성만 해주고
		if(member == null) {
			member=new MemberVO();
		}
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie[] coos = request.getCookies();
		
		if(coos!=null) {
			for(Cookie c : coos) {
				if(c.getName().equals("cookieId")) { member.setId(c.getValue()); }
				if(c.getName().equals("cookiePw")) { member.setPw(c.getValue()); }
				if(c.getName().equals("cookieAuto")) { auto = c.getValue(); }
			}
		}
		// id, pw 일치하는지 DB가서 확인
		int result = memberMapper.idpwCheck(member);
		HttpSession session = getSession();
		// 일치하면 세션 만들고, 쿠키 원하면 만들기
		if(result == 1) {
			if(session.getAttribute("memId") == null) {
				session.setAttribute("memId", member.getId());
			}
			if(auto!=null) { // 자동 로그인 체크, 또는 쿠키에 자동로그인 있으면
				Cookie c1 = new Cookie("cookieId", member.getId()); 
				Cookie c2 = new Cookie("cookieId", member.getPw());
				Cookie c3 = new Cookie("cookieAuto", auto);
				c1.setMaxAge(60*60*24); // 24시간
				c2.setMaxAge(60*60*24); // 24시간
				c3.setMaxAge(60*60*24);
				c1.setPath("/");
				c2.setPath("/");
				c3.setPath("/");
				response.addCookie(c1);
				response.addCookie(c2);
				response.addCookie(c3);
			}
		}
			// 0 = 로그인실패 
			
			return result; // id, pw 체크한 결과 리턴
	}
	
	
	// 세션 가져오기 (메서드로 중복 코드 분리)
	private HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		return session;
	}
	// 세션 생성 
	@Override
	public void createSession(String id) {
		HttpSession session = getSession();
		if(session.getAttribute("memId") != null) { // 세션에 memId 없는지 체크하고 만들기
			getSession().setAttribute("memId",id);
		}
	}
	// 쿠키 생성
	@Override
	public void createCookie() { // service내에서 id, pw 꺼냄
		// 쿠키를 꺼내서 id와 pw값 꺼내 쿠키 생성 (갱신)
		
	}
	
	@Override
	public void logout(HttpServletResponse response) {
		// 세션 삭제
		getSession().invalidate();
		
		// 쿠키 삭제
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie[] coos = request.getCookies();
		
		if(coos!=null) {
			for(Cookie c : coos) {
				if(c.getName().equals("cookieId") || c.getName().equals("cookiePw") || c.getName().equals("cookieAuto")) { 
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
	}

	
	
}
