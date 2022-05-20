package io.github.chocchic.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import io.github.chocchic.model.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>, QuerydslPredicateExecutor<Memo>{
	
	// title이 일치하는 데이터를 조회 - 이름을 이용해서 생성한 쿼리
	List<Memo> findByTitle(String title);
	
	// 직접 쿼리를 작성하는 방법
	@Modifying // 수정을 할때는 이 어노테이션이 있어야함
	@Transactional // 트랜젝션 오류 방지
	@Query("update Memo m set m.title = :title, m.content = :content where m.gno = :gno")
	int update(@Param("title")String title, @Param("content")String content, @Param("gno")Long gno);
	
}
