package com.chocchic.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocchic.board.dto.ReplyDTO;
import com.chocchic.board.persistence.ReplyRepository;
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
	
	// 댓글 삽입
	@PostMapping("")
	// 클라이언트에서 JSON 형태로 보낸 문자열을 ReplyDTO로 변경해서 저장합니다.
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
		log.info("replyDOT : " + replyDTO);
		Long rno = replyService.register(replyDTO);
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
	
	// 댓글 삭제
	@DeleteMapping("/{rno}")
	// 클라이언트에서 JSON형태로 보낸 문자열을 ReplyDTO로 변경해서 저장합니다.
	public ResponseEntity<String> remove(@PathVariable("rno")Long rno){
		replyService.remove(rno);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
	
	// 댓글 수정
	@PutMapping("/{rno}")
	// 클라이언트에서 JSON형태로 보낸 문자열을 ReplyDTO로 변경해서 저장합니다.
	public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO){
		replyService.modify(replyDTO);
		return new ResponseEntity<>("success",HttpStatus.OK);
	}
}
