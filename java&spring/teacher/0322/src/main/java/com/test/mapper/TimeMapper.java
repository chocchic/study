package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.test.model.TestDTO;

public interface TimeMapper {

	//#1. 어노테이션으로 쿼리문 작성/실행 -> 간단한 쿼리문 실행시킬때 
	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	//#2. 인터페이스+XML 쿼리문 작성/실행 
	//    리턴타입 : 쿼리문 실행결과로 받고싶은 타입 작성, 레코드 수에 따라 생각 
	//			레코드가 여러개 결과로 나온다 -> List
	//			레코드가 1개면 resultType과 동일하게 작성
	public String getTime2(); 
	
	
	// select id from test : 레코드가 (2개이상)많이 나옴 -> List
	public List<String> getAllId();
	
	// 회원1명 정보 가져오기 : select * from test where id=#{id} 
	public TestDTO getMember(String id); 
	
	// 회원 추가 : insert into test values();
	public int insertMember(TestDTO dto);
	
	// 회원 한명 비밀번호 수정 modifyPw : update test set pw=#{pw} where id=#{} : <update> 
	public int modifyPw(TestDTO dto);
	// 회원 삭제 deleteMember : delete from test where id=#{id} : <delete> 
	public int deleteMember(String id); 
	
	
	
}
