package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Autowired
	private BoardService boardService; 
	
	@GetMapping("write")
	public void write() {
		System.out.println("write get요청!!!");
	}
	@PostMapping("write")
	public String writePro(BoardVO board, RedirectAttributes rttr) {
		
		
		boardService.register(board);
		log.info(board);
		// 리다이렉트할때 데이터 전송하는 방법중 하나로, 
		// 데이터를 일회성으로만 전달하고 싶을때 이 클래스의 ...Flash 메서 사용. 
		rttr.addFlashAttribute("result", board.getBno());  
		
		
		return "redirect:/board/list"; 
	}
	
	@GetMapping("list")
	public void list(Model model) {
		
		model.addAttribute("list", boardService.getList());
	}
	
	@GetMapping({"content", "modify", "delete"})
	public void content(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", boardService.get(bno)); 
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board) {
		
		boolean res = boardService.modify(board);
		if(res) System.out.println("수정 잘됨");
		else System.out.println("수정 안됨");
		
		return "redirect:/board/list"; 
	}
	@PostMapping("delete")
	public String delete(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); 
		}
		
		return "redirect:/board/list";
	}
	
	
	
	
	

	
}
