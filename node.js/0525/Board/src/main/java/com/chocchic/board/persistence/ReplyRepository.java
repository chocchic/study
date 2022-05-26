package com.chocchic.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chocchic.board.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	// 게시글 번호를 이용해서 삭제하는 메서드 
	@Modifying
	@Query("delete from Reply r where r.board.bno = :bno")
	public void deleteByBno(@Param("bno") Long bno);
}
