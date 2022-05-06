package com.board.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.board.domain.Criteria;
import com.board.domain.ReplyPageDTO;
import com.board.domain.ReplyVO2;
import com.board.service.ReplyService2;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies/")
@Log4j
public class ReplyController {

	@Autowired
	private ReplyService2 replyService;
	
	/*
		consumes : 브라우저에서 요청시 지정한 content-type과 일치해야함. 
		produces : 서버에서 브라우저에 리턴해주는 데이터의 형태
					브라우저에서 요청시 지정한 accept 요청헤더값과 일치 
	*/
	@PostMapping(value="/new", consumes = "application/json", 
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO2 vo) {
		log.info("reply vo : " + vo);
		int result = -1;
		// 새 댓글 : rno == null
		if(vo.getRno() == null) {
			vo.setStep(0);
			vo.setLev(0);
			result = replyService.register(vo);
			log.info("reply insert result : " + result);
		}else { // 댓글의 답글 : rno != null
			// 답글 처리 
			result = replyService.addReply(vo);
		}
		
		
		return result == 1 ? 
					new ResponseEntity<>("success", HttpStatus.OK) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 특정 게시물의 댓글 목록 확인 (댓글 전체) 
	@GetMapping(value="/pages/{bno}/{page}",
			produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE })
	// #4. 메서드 리턴타입과 리턴값 변경 
	//public ResponseEntity<List<ReplyVO2>> getList(
	public ResponseEntity<ReplyPageDTO> getList(
			@PathVariable("page") int page,
			@PathVariable("bno") Long bno) {
		
		log.info("getList 요청!!!!");
		
		Criteria cri = new Criteria(page, 10); 
		log.info("new cri " + cri);
		
		// #4. getListPage() 로 변경 
		//return new ResponseEntity<>(replyService.getList(bno, cri), HttpStatus.OK); 
		return new ResponseEntity<>(replyService.getListPage(bno, cri), HttpStatus.OK); 
		
	}
	
	// 댓글 한개 조회 
	@GetMapping(value="/{rno}",
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ReplyVO2> get(@PathVariable("rno") Long rno){
		log.info("get : " + rno);
		return new ResponseEntity<>(replyService.get(rno), HttpStatus.OK); 
	}
	// 댓글 삭제 
	@DeleteMapping(value="/{rno}")
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		log.info("remove : " + rno);
		return replyService.remove(rno) == 1 ? 
				new ResponseEntity<>("success", HttpStatus.OK) : 
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	// 댓글 수정 
	@PutMapping(value="/{rno}")
	public ResponseEntity<String> modify(
			@PathVariable("rno") Long rno,
			@RequestBody ReplyVO2 vo) {  // json
		
		log.info("modify rno : " + rno);
		log.info("modify vo : " + vo);
		vo.setRno(rno);
		log.info("modify vo after set() : " + vo);
		
		return replyService.modify(vo) == 1 ?
				new ResponseEntity<>("success", HttpStatus.OK) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("test")
	public String test() {
		/* 등록
		IntStream.rangeClosed(2, 10).forEach(i -> {
			ReplyVO vo = new ReplyVO(); 
			vo.setBno(42L); // 실제 board 테이블에 존재하는 bno 번호 사용 
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("java10");
			
			replyMapper.insert(vo); 
		});
		// 전체 조회 
		List<ReplyVO> list = replyMapper.getList(42L);  // 실제 board에 존재하는 bno, 댓글 있는거로 확인 
		list.forEach(vo -> System.out.println(vo));
		
		// 댓글 한개 조회 
		ReplyVO vo = replyMapper.read(10L); // 실제 reply 테이블에 존재하는 rno 번호로 사용 
		System.out.println(vo);
		// 삭제 
		int result = replyMapper.delete(10L);
		System.out.println("Result : " + result);
		*/
		
		/* 수정 : 댓글내용 (reply), 수정일 (updateDate) 
		ReplyVO vo = new ReplyVO(); 
		vo.setRno(11L); // 실제 reply 테이블에 존재하는 rno 번호 사용 
		vo.setReply("수정 댓글!!");
		
		// 댓글번호로 댓글 전체 가져와 
		ReplyVO vo = replyMapper.read(9L);
		// 댓글내용만 set으로 덮어쓰기해서  
		vo.setReply("수정수정수정");
		// 수정 처리 요청 
		int result = replyMapper.update(vo);
		*/
		
		return "insert result : " ;
	}
	
	
	
	
	
	
}
