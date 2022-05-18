package kr.co.adamsoft;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.adamsoft.entity.Memo;
import kr.co.adamsoft.entity.QMemo;

// 스프링 부트 테스트 클래스를 의미하는 어노테이션
@SpringBootTest
public class MemoRepositoryTest {

	@Autowired
	MemoRepository memoRepository;
	
	/*
	@Test
	public void testDependency() {
		System.out.println(memoRepository);
	}
	*/
	/*
	@Test
	public void testInsert() {
		// 정수 스트림(여러 개의 데이터를 순회하면서 사용할 수 있도록 만든 데이터의 통로) 생성
		// forEach는 데이터를 순회하면서 작업을 수행하는 메서드
		// forEach의 매개변수는 매개변수가 1개이고 리턴이 없는 람다가 매개변수
		// 이 때 매개변수는 스트림의 데이터가 순서대로 주입됩니다. 
		
		// List <Integer> list = new ArrayList<>();
		// for(int i = 1; i< 101; i++) { list.add(i);}
		// for(Integer i : list){Memo memo = new Memo(); memo.setMemoText("Sample..."+i); memoRepository.save(memo);}
		// 이 코드와 같은 의미 
		IntStream.rangeClosed(1, 100).forEach(i -> {
			// builder()는 매개변수가 없는 생성자를 이용해서 객체를 생성하고 속성 이름에 값을 바로 대입해서 객체를 생성해주는 메서드 입니다.
			Memo memo = Memo.builder().memoText("Sample..."+i).build();
			memoRepository.save(memo);
		});
	}
	*/
	/*
	@Test
	public void testAllSelect() {
		//테이블의 전체 데이터 가져오기
		List<Memo> list = memoRepository.findAll();
		System.out.println("All Select");
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	*/
	/*
	@Test
	public void testSelectOne() {
		// 기본키를 가지고 데이터 가져오기
		// 존재하는 경우는 데이터가 출력
		System.out.println("Select One");
		Optional<Memo> memo = memoRepository.findById(1L);
		System.out.println(memo);
		
		// 존재하지 않는 경우는 optional.empty
		memo = memoRepository.findById(300L);
		System.out.println(memo);
	}
	*/
	/*
	@Test
	public void testUpdate() {
		//mno가 100이고 memoText가 UpdateText인 인스턴스 생성
		Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
		
		// save의 호출
		// mno의 값이 없으면 삽입이 되고 있으면 수정
		System.out.println(memoRepository.save(memo));
		
		System.out.println(memoRepository.findById(100L));
	}
	*/
	/*
	@Test
	public void testDelete() {
		// 기본키 값을 가지고 데이터를 삭제 
		memoRepository.deleteById(100L);
		
		System.out.println(memoRepository.findById(100L)); //Optional.empty가 뜬다.
	}
	*/
	/*
	@Test
	public void testPaging() {
		Pageable page = PageRequest.of(0, 10);
		Page<Memo> list = memoRepository.findAll(page);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	*/
	/*
	@Test
	public void testSort() {
		// mno의 내림차순 정렬
		Sort sort = Sort.by("mno").descending();
		Pageable page = PageRequest.of(1, 10, sort);
		Page<Memo> list = memoRepository.findAll(page);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	*/
	/*
	@Test
	public void betweenTest() {
		List<Memo> list = memoRepository.findByMnoBetween(70L, 80L);
		for(Memo m : list) {
			System.out.println(m);
		}
	}
	*/
	/*
	@Test
	public void betweenOrderTest() {
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
		for(Memo m : list) {
			System.out.println(m);
		}
	}
	*/
	/*
	@Test
	public void betweenPagingTest() {
		//mno의 내림차순 정렬 후 2페이지부터 10개를 가져오는 Pageable 객체 생성
		Pageable page = PageRequest.of(1, 10, Sort.by("mno").descending());
		List<Memo> list = memoRepository.findByMnoBetween(10L, 50L, page);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	*/
	/*
	// 삭제하는 작업이므로 트랜젝션을 설정해주어야 함 
	@Test
	@Commit
	@Transactional
	public void deleteByMno() {
		memoRepository.deleteByMnoLessThan(10L);
	}
	*/
	/*
	@Test
	public void testUpdateQuery() {
		// 수정하는 메서드 호출
		System.out.println(memoRepository.updateMemoText(11L, "반갑습니다."));
		System.out.println(memoRepository.updateMemoText(Memo.builder().mno(12L).memoText("dkd안녕하세요").build()));
	}
	*/
	
	//@Test
	public void testSelectQuery() {
		Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());
		Page<Memo> page = memoRepository.getListWithQuery(50L, pageable);
		for(Memo m : page) {
			System.out.println(m);
		}
	}
	
	//@Test
	public void testSelectObjectQuery() {
		Pageable pageable = PageRequest.of(0,10,Sort.by("mno").descending());
		//Page<Memo> page = memoRepository.getListWithQueryObject(50L, pageable); // Memo에는 CURRENT_DATE를 담을 공간이 없으므로 error
		Page<Object[]> page = memoRepository.getListWithQueryObject(50L, pageable);
		for(Object[] m : page) {
			System.out.println(Arrays.toString(m));
		}
	}
	
	//@Test
	public void testNativeQuery() {
		List<Object[]> list = memoRepository.getNativeResult();
		for(Object[] ar : list) {
			System.out.println(Arrays.toString(ar));
		}
	}
	
	@PersistenceContext
	EntityManager em;
	
	@Test
	public void queryDslTest() {
		//JPAQuery 생성기를 생성
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		//querydsl에서는 Entity 클래스를 사용하지 않고 Entity 클래스 이름 앞에 Q가 추가된 클래스를 이용해서  쿼리를 생성
		QMemo qMemo = QMemo.memo;
		
		// 쿼리를 생성
		// mno가 12인 데이터를 조회하는 query 생성
		JPAQuery<Memo> query = queryFactory.selectFrom(qMemo).where(qMemo.mno.eq(12L));
		// 쿼리
		List<Memo> list = query.fetch();
		// 결과를 사용
		for(Memo memo: list) {
			System.out.println(memo);
		}
		
	}
} 
