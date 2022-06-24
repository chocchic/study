package com.choc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adam.mobileserver.dto.LoginInfoDTO;
import com.adam.mobileserver.service.ItemService;
import com.adam.mobileserver.service.LoginInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("logininfo")
@RequiredArgsConstructor
public class LoginInfoController {
	private final LoginInfoService loginInfoService;
	
	@GetMapping("/location")
	public ResponseEntity<?> setLoginInfo(LoginInfoDTO dto){
		System.out.println("controller:" + dto);
		String response = null;
		loginInfoService.registerLoginInfo(dto);
		response ="성공";
		return ResponseEntity.ok().body(response);
		
	}
}