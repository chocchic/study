package kr.co.adamsoft;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	// mno을 가지고 데이터를 찾아서 memoText를 수정하는 메서드
	@Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
	//@Query에서 수행하는 문장이 select가 아닐 때 원본에 반영하도록 하기 위해서 설정
	@Modifying
	// 이 메서드 안에서 이루어지는 모든 작업은 하나의 트랜젝션으로 수행하도록 해주는 annotation
	// Repository에 적용하지 않고 대부분은 Service나 Controller에 적용
	@Transactional
	int updateMemoText(@Param("mno")Long mno, @Param("memoText")String memoText);
	
	@Query("update Memo m set m.memoText =:#{#mmm.memoText} where m.mno =:#{#mmm.mno}")
	@Modifying
	@Transactional
	int updateMemoText(@Param("mmm")Memo memo);
	
}