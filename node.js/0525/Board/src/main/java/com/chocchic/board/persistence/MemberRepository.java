package com.chocchic.board.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.board.model.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
}
