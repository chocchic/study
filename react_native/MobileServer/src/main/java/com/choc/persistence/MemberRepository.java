package com.choc.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choc.model.Member;

// JpaRepository를 상속 받을 때는 Entity이름과 Id로 설정한 속성의 자료형이 필요
public interface MemberRepository extends JpaRepository<Member, String> {
	// 회원가입, 로그인 => 회원정보 가져오기, 회원정보 수정, 회원 탈퇴
	
	// 회원 가입을 하는 경우 이름의 중복 체크 하기위한 메서드
	// 기본키를 제외한 다른 컬럼을 가지고 조회를 할때는 몇개가 나올지 모르기 때문에 리턴타입은 List
	List<Member> findMemberByName(String name);
}
