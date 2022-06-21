package com.choc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.choc.dto.ItemDTO;
import com.choc.dto.MemberDTO;
import com.choc.dto.PageRequestItemDTO;
import com.choc.dto.PageResponseItemDTO;
import com.choc.service.ItemService;
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
	//@Test
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
	
	// 멤버 수정 테스트
	//@Test
	public void updateMember() {
		MemberDTO dto = MemberDTO.builder().email("youremail@site.com").name("NAME").password("pw1234")
				.imageurl("user.png").build();
		String result = m.updateMember(dto);
		System.out.println(result);
	}
	
	// 멤버 삭제 테스트
	//@Test
	public void deleteMember() {
		MemberDTO dto = MemberDTO.builder().email("youremail@site.com").build();
		String result = m.deleteMember(dto);
		System.out.println(result);
	}
	
	@Autowired
	private ItemService itemService;
	
	// 데이터 삽입
	//@Test
	public void testregisterItem() {
		for(int i = 0; i<100; i++) {
			ItemDTO dto = ItemDTO.builder().itemname("apple_"+i).price(3000).description("사과_"+i).pictureurl("apple_"+i+".png")
					.email("dntksemfdj473@gmail.com").build();
			Long itemid = itemService.registerItem(dto);
			System.out.println(itemid);
		}
	}
	// 데이터 1개 가져오기
	//@Test
	public void testGetItem() {
		ItemDTO dto = ItemDTO.builder().itemid(101L).build();
		System.out.println(itemService.getItem(dto));
	}
	
	// 페이지 단위로 가져오기
	//@Test
	public void testGetList() {
		PageRequestItemDTO dto = PageRequestItemDTO.builder().page(2).size(10).build();
		PageResponseItemDTO result = itemService.getList(dto);
		System.out.println(result);
	}
	
	// 데이터 수정
	//@Test
	public void testUpdateItem() {
		ItemDTO dto = ItemDTO.builder().itemid(16L).itemname("apple_0616수정").price(6000).description("수정된 사과")
				.pictureurl("apple__0616수정")
				.email("dntksemfdj473@gmail.com").build();
		Long itemid = itemService.updateItem(dto);
		System.out.println(itemid);
	}
	
	// Item 삭제
	@Test
	public void testDeleteItem() {
		ItemDTO dto = ItemDTO.builder().itemid(23L).build();
		Long itemid = itemService.deleteItem(dto);
		System.out.println(itemid);
	}
}
