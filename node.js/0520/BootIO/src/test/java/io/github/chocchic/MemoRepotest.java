package io.github.chocchic;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import io.github.chocchic.model.Memo;
import io.github.chocchic.model.QMemo;
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
	
	// 400데이터 업데이트
	//@Test
	public void updateM() {
		int result = m.update("제목바꿔버리기~~", "내용도 수정하기", 400L);
		System.out.println("수정된 row : " + result);
	}
	
	// title에 1이 포함된 데이터 조회
	//@Test
	public void testQuery1() {
		//gno의 내림차순으로 정렬에서 0번 페이지의 데이터 중 10개를 가져오는 Pageable
		Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());
		
		//querydsl이 만들어준 클래스를 이용해서 쿼리 생성
		//querydsl은 Entity에 명령을 직접 수행하게 끔하지 않고 Q로 시작하는 별도의 메모리 공간에 명령을 내림
		QMemo qm = QMemo.memo;
		// 검색할 조건을 가지는 JPQL(SQL) 생성기 
		BooleanBuilder builder = new BooleanBuilder();
		
		//title에 1이 포함된 쿼리를 생성
		// title에 1이포함된 데이터를 조회하는 쿼리
		BooleanExpression expression = qm.title.contains("1");
		// 실제로 쿼리 생성 where title like "%1%";
		builder.and(expression);
		
		//퀴리를 수행
		Page<Memo> result = m.findAll(builder, pageable);
		
		for(Memo mm : result) {
			System.out.println(mm);
		}
	}
	
	// title 또는 content에 1이 포함되어 있고 gno가 0보다 큰 데이터 조회
	@Test
	public void testQuery2() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("gno").ascending());
		
		QMemo qm = QMemo.memo;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		// 조건을 생성
		String keyword = "1";
		// title에 포함된 것
		BooleanExpression expTitle = qm.title.contains(keyword);
		// content에 포함된 것
		BooleanExpression expContent = qm.content.contains(keyword);
		// 위 두 조건을 or로 묶어줌 
		BooleanExpression expAll = expTitle.or(expContent);
		
		builder.and(expAll);
		// gno가 0보다 큰 조건을 and로 묶어주기
		// 임의로 설정한 조건이므로 다른 조건도 가능
		// 작은 조건이라면 lt 
		builder.and(qm.gno.gt(0L));
		
		//퀴리를 수행
		Page<Memo> result = m.findAll(builder, pageable);
		
		for(Memo mm : result) {
			System.out.println(mm);
		}
	}
}
