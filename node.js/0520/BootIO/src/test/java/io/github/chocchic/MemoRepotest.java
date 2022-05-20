package io.github.chocchic;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.chocchic.model.Memo;
import io.github.chocchic.persistence.MemoRepository;

@SpringBootTest
public class MemoRepotest {

	@Autowired
	private MemoRepository m;
	
	//@Test
	public void insertMemo() {
		// 300개의 정수 모임을 생성하고 순회
		IntStream.rangeClosed(1, 300).forEach(i ->{
			// 데이터 생성
			Memo memo = Memo.builder().title("title_"+i).content("content_"+i).writer("writer_"+i).build();
			// 데이터 삽입
			m.save(memo);
		});
		/*
		select * from memo;
		DB에서 위 구문으로 확인
		 */
		
	}
	
	//데이터 수정 테스트
	//@Test
	public void updateMemo() {
		// 수정할 데이터 가져오기
		Optional<Memo> result = m.findById(300L);
		
		if(result.isPresent()) {
			Memo memo = result.get();
			System.out.println("변경 전 내용 : "+memo);
			memo.changeTitle("Changed 제목");
			memo.changetContent("Changed 내용");
			m.save(memo);
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		/*
		 * 데이터베이스에서 아래 구문으로 변경되었는지 확인
		 * select * from memo where gno = 300;
		 * */	
	}
	
	//데이터 삭제 테스트
	//@Test
	public void deleteMemo() {
		// 삭제할 데이터 가져오기
		Optional<Memo> result = m.findById(800L);
		
		if(result.isPresent()) {
			Memo memo = result.get();
			System.out.println("삭제할 내용 : "+memo);
			m.delete(memo);
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
		/*
		 * 데이터베이스에서 아래 구문으로 변경되었는지 확인
		 * select * from memo where gno = 800; 
		 * 있는 번호 였다면 데이터가 존재하지 않습니다라는 문구가 안뜨고 검색하면 null이 나올 것임
		 * */
	}
	
	// title이 title_1인 데이터 조회
	//@Test
	public void findByTitle() {
		List<Memo> list = m.findByTitle("title_1");
		System.out.println(list); // 검색이 아무것도 안되면 대괄호만 나온다
	}
	
	// 
	@Test
	public void updateM() {
		int result = m.update("제목바꿔버리기~~", "내용도 수정하기", 400L);
		System.out.println("수정된 row : " + result);
	}
}
