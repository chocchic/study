package com.test.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component // 스프링이 관리해야 할 객체라고 알려줌
@Data // lombok의 getter/setter, 생성자 등 자동으로 생성해주는 어노테이션
public class Chef {
	private String name;
	
}
// http://localhost:8080/controller
