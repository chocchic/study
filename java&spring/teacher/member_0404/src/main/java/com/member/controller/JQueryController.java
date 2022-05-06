package com.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jq/")
public class JQueryController {

	@GetMapping("jq01")
	public String jq01() {
		
		return "jq/jq03"; 
	}
	
	
}
