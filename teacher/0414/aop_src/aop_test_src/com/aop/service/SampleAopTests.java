package com.aop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

//#5. SampleService를 테스트하기위한 테스트 클래스 생성, 
//		어노테이션3개, @Setter service, testClass()테스트메서드 추가 
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleAopTests {

	// java.lang.Error : unresolved... 나오며 잘 실행안되면 매번 테스트전에 Project > clean!! 
	
	@Autowired
	private SampleService service;
	
	/*
	@Test
	public void testClass() {
		// 가장먼저 테스트할 것은 AOP 설정한 Target에대해 Proxy가 정상적으로 만들어졌는지 확인하는 것이다. 
		// aspectj-autoproxy 가 잘 동작하고, LogAdvice 설정에 문제가 없다면 service 변수의 클래스는 
		// 단순 SampleImpl의 인스턴스가 아닌 Proxy 클래스의 인스턴스가 된다. 
		// 아래 로그 출력으로 테스트 실행해 확인해보자. 
		log.info(service);
		log.info(service.getClass().getName());
		//INFO : com.arimkim.service.SampleServiceTests - com.arimkim.service.SampleServiceImpl@2ca26d77
		//INFO : com.arimkim.service.SampleServiceTests - com.sun.proxy.$Proxy21  <- JDK 다이나믹프록시 기법적용됨 
		// 단순히 service 출력하면 일반 SampleServiceImpl 객체 처럼 보이나 
		// getClass().getName()하면 정확하게 파악해야한다. 
	}*/
	
	/// #6. service의 doAdd() 메서드 검증할 테스트 메서드 추가 
	// #7. #10. 실행만,
	@Test
	public void testAdd() throws Exception {
		log.info(service.testAdd("123", "456")); // doAdd호출 -> aop before -> doAdd실행 
		// 실행 -> 
		//INFO : com.arimkim.aop.LogAdvice - ================
		//INFO : com.arimkim.service.SampleServiceTests - 579
	} 

	//#9. AfterThrowing 테스트할 테스트 메서드 추가 
	@Test
	public void testAddError() throws Exception {
		// #10. 에러는 주석 
		//log.info(service.testAdd("123", "ABC")); // 뒤쪽은 Integer.parseInt() 에서 에러 발생 
	}
	
	
	
	
}
