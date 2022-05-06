package com.test.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.mapper.TimeMapper;

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
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime()); // 인터페이스의 메서드 호출하여 쿼리문 실행하기
	}
	
	@Test
	public void testGetTime2() {
		log.info("****************************");
		log.info(timeMapper.getTime2());
		log.info("****************************");
	}*/
	
	@Test
	public void testGetAllId() {
		log.info("****************");
		List<String> result = timeMapper.getAllId();
		log.info(result);
		log.info("****************");
	}
	
}
