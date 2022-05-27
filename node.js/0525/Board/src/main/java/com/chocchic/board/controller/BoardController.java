package com.chocchic.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chocchic.board.BoardDTO;
import com.chocchic.board.dto.PageRequestDTO;
import com.chocchic.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	// 목록보기 요청을 처리할 메서드
	@GetMapping({"/","/board/list"})
	public String list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("목록보기 요청"+pageRequestDTO);
		model.addAttribute("result",boardService.getList(pageRequestDTO));
		
		return "/board/list";
	}
	
	// 게시물 작성을 처리할 메서드
	@GetMapping("/board/register")
	public void register() {
		log.info("게시물 등록으로 이동");
	}
	
	@PostMapping("/board/register")
	public String register(BoardDTO dto, RedirectAttributes rAttr) {
		log.info("게시물 처리중"+dto);
		// 게시물 등록
		Long bno = boardService.register(dto);
		// View에 데이터 전달
		rAttr.addFlashAttribute("msg",bno+" insert");
		return "redirect:/board/list";
	}
	
	// 상세보기 처리를 위한 메서드
	@GetMapping({"/board/read", "/board/modify"})
	// @ModelAttribute("이름")는 파라미터를 받아서이름으로 다음 요청에게 넘겨주는 역항르 수행
	public void read(@ModelAttribute("requestDTO")PageRequestDTO pageRequestDTO, Model model, Long bno) {
		log.info("상세보기 처리중.." + bno);
		BoardDTO boardDTO = boardService.getBoard(bno);
		model.addAttribute("dto",boardDTO);
	}
}
