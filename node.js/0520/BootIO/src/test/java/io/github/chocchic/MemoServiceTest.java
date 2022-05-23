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
	
	// 목록 보기 테스트
	@Test
	public void testList() {
		PageRequestDTO pr = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<MemoDTO, Memo> resultDto = m.getList(pr);
		for(MemoDTO mm : resultDto.getDtoList()) {
			System.out.println(mm);
		}
	}
	
}
