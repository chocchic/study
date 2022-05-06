package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/")
public class TestController {

	@GetMapping("test01")
	public void test() {
		
	}
	@GetMapping("test02")
	public void test02() {
		
	}
	
	
}
