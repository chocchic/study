package com.choc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.choc.service.MemberService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private MemberService m;
	
	
}
