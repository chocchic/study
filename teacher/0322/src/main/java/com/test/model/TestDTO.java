package com.test.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TestDTO {
	private String id; 
	private String pw;
	private Integer age;
	private Timestamp reg;
}
