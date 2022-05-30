package com.chocchic.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocchic.board.dto.ReplyDTO;
import com.chocchic.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
	private final ReplyService replyService;
	
	// 댓글 가져오기
	@GetMapping(value="/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno")Long bno){
		log.info("댓글 가져오기 bno : ",bno);
		List<ReplyDTO> list = replyService.getList(bno);
		return new ResponseEntity(list, HttpStatus.OK);
	}
}
