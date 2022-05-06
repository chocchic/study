package com.test.sample;

import java.util.Date;

import org.springframework.stereotype.Component;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Component // 스프링이 관리할 객체!

//@Data // Setter, Getter 외 다수가 포함되있으니까 어노테이션 수를 줄일 수 있음
@Setter
@Getter
public class HelloBean {
	private int num;
	private String msg;
	private Date reg;
	
	public HelloBean(int num, String msg) {
		this.num = num;
		this.msg = msg;
	}
}
