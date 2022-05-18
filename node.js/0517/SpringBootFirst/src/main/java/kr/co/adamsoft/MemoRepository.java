package kr.co.adamsoft;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.adamsoft.entity.Memo;

// Memo Entity에 대한 작업을 수행하기 위한 Repository 인터페이스
public interface MemoRepository extends JpaRepository<Memo, Long>{
	// mno값이 70에서 80사이인 데이터를 조회
	List<Memo> findByMnoBetween(Long first, Long second);
	
	// mno값이 70에서 80사이인 데이터를 mno를 내림차순 정렬하여 조회
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long first, Long second);
	
	// mno값이 from에서 to사이인 데이터를 조회 - 페이징 적용
	List<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	
	// mno가 매개변수보다 작은 데이터 삭제
	void deleteByMnoLessThan(Long num);
}