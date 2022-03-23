package com.test.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.mapper.TestMapper;
import com.test.model.TestDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/day06/")
@Log4j
public class TestController6 {
	@GetMapping("form")
	public void form() {
		System.out.println("form requested");
	}
/*	
	//http://localhost:8080/day06/pro?id=java&pw=1234&age=10
//	@RequestMapping(value="pro", method = RequestMethod.POST)
//	@RequestMapping(value="pro", params="id=java") // 파라미터에 id가 java로 반드시 와야 한다는 제약을 줌
//	@RequestMapping(value="pro", params={"id=java","pw=1234"}) // 파라미터의 유효성 검사 가능
//	@RequestMapping(value="pro", params={"id=java","pw"}) // pw 파라미터가 넘어오면 ok 값은 상관 X
//	@RequestMapping(value="pro", params={"id=java","pw","!age"}) // age라는 이름의 파라미터는 넘어오면 안된다. 
	@RequestMapping("pro")							// id 파라미터가 넘어지 않을 경우 default 값 지정 가능
//	public String pro(@RequestParam(value="id", defaultValue = "hello")String id, @RequestParam(value="pw") String pw)
	public String pro(@RequestParam(value="id", required=true)String id, @RequestParam(value="pw") String pw){
												// id값 필수
												
		System.out.println(id);
		System.out.println(pw);
		return "day06/pro";
	}
	*/
	@Autowired
	private TestMapper testMapper;
	// form에서 넘어온 데이터 처리 요청
	@PostMapping("pro")
	public void pro(TestDTO dto) {
		System.out.println("pro requested");
		// DB 저장
		testMapper.insertMember(dto);
	}
	
	@GetMapping("test")
	public void test(Model model) {
//		int count = testMapper.countMember();
//		System.out.println("총 회원 수 : "+count);
//		model.addAttribute("result",count);
	
//		int maxage = testMapper.maxAge();
//		System.out.println("maxage : " + maxage);
//		model.addAttribute("result",maxage);
		
//		List<TestDTO> table = testMapper.selectAll();
//		System.out.println(table);
////		for(TestDTO t : table) {
////			System.out.println(t);
////		}
//		table.forEach(dto-> System.out.println(dto));
//		model.addAttribute("result",table);
		
//		TestDTO dto = testMapper.selectOne("spring03");
//		System.out.println(dto);
//		model.addAttribute("result", dto);
		
//		Timestamp ts = testMapper.getReg("spring02");
//		System.out.println(ts);
		
//		testMapper.updateMember("spring01", "55555", 49);
		
		// **** 동적 쿼리 ****
//		int count = testMapper.selectIf(10);
//		int count = testMapper.selectIf();
//		System.out.println(count);
		
		//id 중복 여부 확인
		/*
		TestDTO dto = new TestDTO();
		dto.setId("spring02");
		int count = testMapper.selectIf2(dto);
		System.out.println("count : " + count);
		if(count > 0) {
			System.out.println(dto.getId() + "는 이미 사용중인 아이디입니다.");
		}else {
			System.out.println(dto.getId() + "는 사용 가능합니다.");
		}
		*/
		
		// id,pw 일치하는지 확인( 로그인 체크)
		/*
		TestDTO dto = new TestDTO();
		dto.setId("spring01");
		dto.setPw("1234");
		int count = testMapper.selectIf2(dto);
		System.out.println("count : " + count);
		if(count > 0) {
			System.out.println("로그인 성공!!");
		}else {
			System.out.println("id 또는 pw를 잘못 입력하셨습니다.");
		}
		*/
		
		/*
		// age가 ArrayList에 있는지 확인해서 있는 사람의 정보만 보여주기
		ArrayList list = new ArrayList();
		list.add(10);
		list.add(20);
		list.add(30);
		List<TestDTO> resultlist = testMapper.selectIn(list);
		System.out.println(resultlist);
		resultlist.forEach(dto -> System.out.println(dto));
		*/
		/*
		//selectKey에서 age를 채워줌.
		TestDTO dto = new TestDTO();
		dto.setId("spring04"); dto.setPw("1515");
		testMapper.insertSK(dto);
		System.out.println("dto : " + dto);
		*/
		/*
		// id에 특정 문자가 포함되어있는 사람 정보 모두 추출
		List<TestDTO> dtoList = testMapper.selectLike("j");
		System.out.println(dtoList);
		dtoList.forEach(dto -> System.out.println(dto));
		*/
		
		// 컬럼명(검색 기준)과 키워드 두개를 보내서 일치하는 것 찾기
		HashMap map = new HashMap();
		map.put("column", "age");	// select 박스로 사용자가 선택하 검색 기준
		map.put("keyword", "0");	// 검색 키워드 작성한 것
		List<TestDTO> dtoList = testMapper.searchBBS(map);
		System.out.println(dtoList);
		dtoList.forEach(dto -> System.out.println(dto));
	}
}
