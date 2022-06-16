package com.choc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.choc.dto.MemberDTO;
import com.choc.service.MemberService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private MemberService m;
	
	// 회원가입 테스트
	//@Test
	public void testRegisterMember() {
		// 처음 추가를 할때는 성공, email과 name이 중복된 데이터면 실패
		MemberDTO dto = MemberDTO.builder().email("youremail@site.com").password("yourpw")
				.name("yourname").imageurl("yourimg.png").build();
		
		String result = m.registerMember(dto);
		System.out.println(result);
	}
	
	// 회원 정보 가져오기
	//@Test
	public void testGetMember() {
		MemberDTO dto = MemberDTO.builder().email("youremail@site.com").build();
		MemberDTO result = m.getMember(dto);
		System.out.println(result);
	}
	
	// 로그인 테스트
	@Test
	public void testLoginMember() {
		MemberDTO dto = MemberDTO.builder().email("youremail@site.com").password("yourpw").build();
		MemberDTO result = m.loginMember(dto);
		System.out.println(result);
		
		// 오늘 날짜를 생성
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(date);
		try {
			// true값 주면 같은 이름의 파일에 append
			FileOutputStream fos = new FileOutputStream("C:\\Users\\SAMSUNG\\Desktop\\java\\chocchic.github.io\\react_native\\MobileServer\\"+ d + ".txt", true);
			PrintWriter pw  = new PrintWriter(fos);
			pw.println("내용");
			pw.flush();
			pw.close();
		}catch(Exception e) {
			
		}
	}
}
