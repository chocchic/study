package com.chocchic.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.board.model.Board;

public interface BoardRepository extends JpaRepository<Board, String> {

}
