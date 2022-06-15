package com.choc;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.choc.model.Member;
import com.choc.persistence.MemberRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private MemberRepository m;
	
	// Member에 데이터 삽입
	@Test
	public void testRegisterMember() {
		/*
		Member member = Member.builder().email("dntksemfdj473@gmail.com")
				.password("159753").name("촉촉한초코칩").imageurl("chocochip.png").build();
		m.save(member);
		*/
		
		// BCrypt 사용해보기
		/*
		String password = BCrypt.hashpw("159753", BCrypt.gensalt()); 
		// 같은 데이터를 가지고 암호화해도 매번 다른 값이 나옴
		System.out.println(password);
		System.out.println(BCrypt.checkpw("159753", password)); // 평문이 같으면 true
		*/
		// password에 String 형태로 hash된 값이 들어간 것이 확인됨
		String password = BCrypt.hashpw("159753", BCrypt.gensalt());
		Member member = Member.builder().email("dntksemfdj473@gmail.com")
				.password(password).name("촉촉한초코칩").imageurl("chocochip.png").build();
		m.save(member);
	}
	
	// 회원 정보 가져오기 - 수정이나 로그인에서 사용  
	//@Test
	public void testGetMember() {
		Optional<Member> optional = m.findById("dntksemfdj473@gmail.com");
		if(optional.isPresent()) {
			Member member = optional.get();
			System.out.println(member); // 로그인은 데이터를 가져와서 비교하면 됨!
		}else {
			System.out.println("존재하지 않는 데이터입니다.");
		}
	}
	
	// 데이터 수정
	//@Test
	public void testUpdateMember() {
		String password = BCrypt.hashpw("111111", BCrypt.gensalt());
		Member member = Member.builder().email("dntksemfdj473@gmail.com")
				.password(password).name("칙촉").imageurl("user.png").build();
		
		m.save(member);
	}
	
	// 데이터 삭제
	//@Test
	public void testDeleteMember() {
		// 2가지 방법 존재
		Member member = Member.builder().email("dntksemfdj473@gmail.com").build();
		m.delete(member);
		

		//m.deleteById("dntksemfdj473@gmail.com");
	}
}
