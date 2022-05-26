package com.chocchic.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.board.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
}
