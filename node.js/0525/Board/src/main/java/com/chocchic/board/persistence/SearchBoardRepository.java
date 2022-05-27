package com.chocchic.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.board.model.Board;

public interface SearchBoardRepository {
	// QueryDSL을 적용한 SQL을 실행하기 위한 메서드
	public Board search();
}
