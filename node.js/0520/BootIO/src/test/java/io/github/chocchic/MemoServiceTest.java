package io.github.chocchic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.chocchic.dto.MemoDTO;
import io.github.chocchic.dto.PageRequestDTO;
import io.github.chocchic.dto.PageResponseDTO;
import io.github.chocchic.model.Memo;
import io.github.chocchic.service.MemoService;

@SpringBootTest
public class MemoServiceTest {
	@Autowired
	private MemoService m;
	
	//@Test
	public void testInsert() {
		MemoDTO dto = MemoDTO.builder().title("데이터삽입테스트1").content("insert success").writer("Service").build();
		System.out.println(m.insertMemo(dto));
		// 데이터베이스에 가서 select * from memo order by gno desc;로 확인
	}
	
	// 목록 보기 테스트 - 목록 테스트
	//@Test
	public void testList() {
		PageRequestDTO pr = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<MemoDTO, Memo> resultDto = m.getList(pr);
		for(MemoDTO mm : resultDto.getDtoList()) {
			System.out.println(mm);
		}
	}
	
	// 목록 보기 테스트 - 목록과 페이지 번호 테스트
	@Test
	public void testPageList() {
		PageRequestDTO pr = PageRequestDTO.builder().page(51).size(10).build();
		PageResponseDTO<MemoDTO, Memo> resultDto = m.getList(pr);
		for(MemoDTO mm : resultDto.getDtoList()) {
			System.out.println(mm);
		}
		
		// 이전과 다음 페이지 존재여부
		System.out.println("이전 : " + resultDto.isPrev());
		System.out.println("다음 : " + resultDto.isNext());
		// 전체 페이지 개수
		System.out.println("전체 페이지 개수 : " + resultDto.getTotalPage());
		// 페이지 번호 목록
		System.out.println(resultDto.getPageList());
	}
	
	@Test
	public void testListSearch() {
		PageRequestDTO pr = PageRequestDTO.builder().page(1).size(10).type("t").keyword("개행").build();
		PageResponseDTO<MemoDTO, Memo> result = m.getList(pr);
		System.out.println(result.getDtoList());	
	}
}
