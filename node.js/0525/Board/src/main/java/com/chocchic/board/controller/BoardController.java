package com.chocchic.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		return "/board/list";
	}
	
	
}
