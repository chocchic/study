package com.aop.service;

// #1. aop 테스트를 위한 서비스 인터페이스 생성, 추상메서드 추가 
public interface SampleService {

	// 단순히 문자열 변환해서 더하기 연산하는 단순 작업 
	public Integer testAdd(String str1, String str2) throws Exception; 
	
}
