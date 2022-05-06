package com.member.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {
	
	private String id; 
	private String pw; 
	private String name; 
	private String email;
	private String gender;
	private Integer age;	
	private Timestamp reg;
	private Timestamp updateDate; 
	private String enabled; // 활동중 "1", 비활동중 "0"  
	private List<AuthVO> authList;
	
}
