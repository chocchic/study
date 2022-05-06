package com.test.sample;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

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
