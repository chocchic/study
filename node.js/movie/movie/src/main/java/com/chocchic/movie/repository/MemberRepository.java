package com.chocchic.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.movie.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
