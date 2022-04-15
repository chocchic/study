package com.aop.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

// #3. Advice(횡단관심사인 공통기능 구현 클래스 ex.log 찍기)  클래스 생성, 어노테이션3개, before메서드 추가 
@Aspect		// 해당 클래스의 객체가 Aspect 를 구현한 것임을 나타냄 
@Log4j
@Component	// AOP와 관련은 없고, 스프링에서 Bean으로 인식하기 위해 사용 
public class LogAdvice {

	// @Before : Before 어드바이스를 구현한 메서드라고 알림 (@After,@AfterReturning 등 동일한 방식으로 적용) 
	// Before() 안에는 Pointcut 지정, @Pointcut으로 별도로 지정해서도 사용가능. 
	//	내부 "execution...." 은 AspectJ의 표현식이다, execution의 경우 접근제한자와 특정 클래스의 메서드를 지정할 수 있다. 
	//	(접근지정자) 리턴타입 (패키지명)클래스.메서드명(매개변수) 
	@Before("execution(* com.aop.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("========aop========");
	}
	
	//#7. args를 이용하여 파라미터 값을 로그로 출력가능한 어드바이스 추가 
	//				doAdd(String, String)메서드 명시하고, 파라미터 타입 지정, 
	//				& args(...) 부분에는 변수명을 지정, 이 2종류의 정보를 이용해 메서드의 파라미터를 설정한다. 
	@Before("execution(* com.aop.service.SampleService*.testAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	//#8. (잘못된 파라미터 전달등)예외 발생후 실행되는 어드바이스 추가 
	// 		pointcut과 throwing 속성을 지정, 변수이름을 exception으로 지정함. 
	@AfterThrowing(pointcut = "execution(* com.aop.service.SampleService*.*(..))",
			throwing = "exception")
	public void logException(Exception exception) {
		
		log.info("Exception......!!!!");
		log.info("exception : " + exception);
	}
		
	//#10. Around 어드바이스 추가 
	@Around("execution(* com.aop.service.SampleService*.*(..))")
	public Object logTime(ProceedingJoinPoint j) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target : " + j.getTarget());
		log.info("Param : " + Arrays.toString(j.getArgs()));
		
		Object result = null;
		try {
			result = j.proceed();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		log.info("Time : " + (end-start));
		
		return result;
	}
	
	
	
		
	
}
