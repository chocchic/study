package com.member.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	private String id; 
	private String pw; 
	private String name; 
	private String email;
	private String gender;
	private Integer age;	// Integer age = null	// int age; 자동초기화 0  
	private Timestamp reg;
}
