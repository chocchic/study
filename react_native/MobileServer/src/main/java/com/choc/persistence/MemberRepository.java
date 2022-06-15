package com.choc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choc.model.Member;

// JpaRepository를 상속 받을 때는 Entity이름과 Id로 설정한 속성의 자료형이 필요
public interface MemberRepository extends JpaRepository<Member, String> {
	// 회원가입
	// 로그인
	// 회원 정보 가져오기
	// 회원 정보 수정
	// 회원 탈퇴
}
