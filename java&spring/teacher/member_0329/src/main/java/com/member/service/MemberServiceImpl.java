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

	/* 로그인 처리 (구번전)  
	@Override
	//public int idPwCheck(MemberVO member, HttpSession session) { // 세션방법#1.
	public int idPwCheck(MemberVO member, String auto, HttpServletResponse response) { // 세션 방법#2.
		int result = memberMapper.idPwCheck(member);
		System.out.println("result service :" + result);
		
		// result 결과를 확인하고 1 = 로그인 = session에 속성추가 
		if(result == 1) {
			// session에 memId라는 이름으로 사용자 id 추가 --> 로그인확인용으로 사용할것임 
			getSession().setAttribute("memId", member.getId()); 
			if(auto != null) { // 자동로그인 체크했다 -> 쿠키 만들기 
				Cookie c1 = new Cookie("cookieId", member.getId()); 
				Cookie c2 = new Cookie("cookiePw", member.getPw()); 
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
	} */

	// 회원 정보 가져오기 
	@Override
	//public MemberVO getMember(String id) { //#1.
	public MemberVO getMember() { // #2.
		// #2. 
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		//HttpSession session = request.getSession(); 
		String id = (String)(getSession().getAttribute("memId")); 
		// #1.#2.
		MemberVO member = memberMapper.getMember(id);
		return member; // Controller한테 전달해줄게 
	}

	// 회원정보 수정 처리 구현 
	@Override
	public int modifyMember(MemberVO member) {
		
		String id = (String)(getSession().getAttribute("memId")); 
		member.setId(id);  // member에 id값도 체워서 mapper에 보내기 
		
		int result = memberMapper.updateMember(member);
		// 비밀번호 갱신했으면, 쿠키 변경 
		
		
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
			//session.invalidate();
			logout(response); // 세션지우고, 쿠키도 삭제 
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
		
		// member가 안들어올경우 객체 생성만 해주고 (변수에 값은 비어있음) 
		if(member == null) {
			member = new MemberVO();
		}
		// 쿠키 꺼내서, id,pw,auto가 맞으면 member객체와 auto변수에 체워주기 
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		Cookie[] coos = request.getCookies(); 
		if(coos != null) {
			for(Cookie c : coos) { // 쿠키가 있으면 member객체에 id,pw 체우기 
				if(c.getName().equals("cookieId")) member.setId(c.getValue());
				if(c.getName().equals("cookiePw")) member.setPw(c.getValue());
				if(c.getName().equals("cookieAuto")) auto = c.getValue();
			}
		}
		// id, pw 일치하는지 DB가서 확인 
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
				}
			}
		}
		
	}

	// 아이디 중복확인 처리 메서드 구현 
	@Override
	public int idAvail(String id) {
		
		int count = memberMapper.idCount(id); 
		
		return count;
	}
	

	
	
	
	

	
	
	
	
	

	
}
