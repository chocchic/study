package com.test.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.mapper.TimeMapper;
import com.test.model.TestDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {

	@Setter(onMethod_=@Autowired)
	private TimeMapper timeMapper;
	
	/*
	@Test
	public void testGetAllId() {
		log.info("****************************************");
		List<String> list = timeMapper.getAllId(); 
		log.info(list);
		log.info("****************************************");		
	}
	
	@Test
	public void testGetMember() {
		
		TestDTO dto = timeMapper.getMember("java");
		log.info(dto);
		log.info(dto.getId());
		
	}
	*/
	
	/*
	@Test
	public void testInsertMember() {
		TestDTO dto = new TestDTO(); 
		dto.setId("spring01");
		dto.setPw("1111");
		dto.setAge(10);
		int result = timeMapper.insertMember(dto);
		log.info("result ************ " + result);
	}*/
	/*
	@Test
	public void testModifyPw() {
		
		TestDTO dto = new TestDTO(); 
		dto.setId("java");
		dto.setPw("9999");
		int result = timeMapper.modifyPw(dto);
		log.info(result);
	}*/
	
	@Test
	public void testDeleteMember() {
		
		int result = timeMapper.deleteMember("spring02");
		log.info("delete result : " + result);
	}
	
	
	
	
	/*
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime()); // 인터페이스의 메서드 호출하여 쿼리문 실행하기 
	}
	@Test
	public void testGetTime2() {
		log.info("************************************************");
		log.info(timeMapper.getTime2());
		log.info("************************************************");
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
}
