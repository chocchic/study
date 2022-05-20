package io.github.chocchic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoPageController {
	@GetMapping("/")
	public String main() {
		return "main";
	}
}
