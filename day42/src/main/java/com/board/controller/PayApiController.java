package com.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.domain.PayInfoVO;

@Controller
@RequestMapping("/payApi/*")
public class PayApiController {
	@GetMapping("payForm")
	public void payForm() {
		System.out.println("payForm 요청!!");
	}
	@PostMapping(value="insertPayInfo", consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> insertPayInfo(@RequestBody PayInfoVO info){
		System.out.println("pay info : " + info);
		
		// DB 저장 코드 실행 Service -> Mapper -> DB 저장 처리
		int result = 1; // 테스트로 db저장 잘되서 결과 1 받았다는 가정하에
		
		// DB 저장 처리 결과에 따라 성공하면 success문자열과 http 상태코드 200
		// 실패하면 상태코드 500으로만 전달
		return result == 1 ? new ResponseEntity<String>("success",HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
