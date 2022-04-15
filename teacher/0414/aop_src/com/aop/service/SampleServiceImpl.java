package com.aop.service;

import org.springframework.stereotype.Service;

// #1. 구현 클래스와 메서드 오버라이딩 + @Service 어노테이션 
@Service
public class SampleServiceImpl implements SampleService {

	// 단순히 문자열 변환해서 더하기 연산하는 단순 작업 
	@Override
	public Integer testAdd(String str1, String str2) throws Exception {

		return Integer.parseInt(str1) + Integer.parseInt(str2);
	}

	
}
