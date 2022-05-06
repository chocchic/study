package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	// 브라우저 -> C -> S -> M -> DB 
	
	@Autowired
	private BoardService boardService; 
	
	@GetMapping("write")
	@PreAuthorize("isAuthenticated()")	// 로그인한 사용자만 접근 가능하게 (xml에 경로 접근제한 설정대신 어노테이션으로 설정) 
	public void write() {
		System.out.println("write get요청!!!");
	}
	@PostMapping("write")
	@PreAuthorize("isAuthenticated()")
	public String writePro(BoardVO board, RedirectAttributes rttr) {
		boardService.register(board);
		log.info(board);
		rttr.addFlashAttribute("result", board.getBno());  
		return "redirect:/board/list"; 
	}
	
	@GetMapping("list") // list 요청시 pageNum, listQty 가 들어와야함.
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", boardService.getList(cri));
		int total = boardService.getTotal(cri); 
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	@GetMapping("content")
	public void content(@RequestParam("bno") Long bno, @ModelAttribute("cri")Criteria cri, Model model) {
		model.addAttribute("board", boardService.get(bno)); 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping({"modify","delete"})
	public void modify(@RequestParam("bno") Long bno, @ModelAttribute("cri")Criteria cri, Model model) {
		model.addAttribute("board", boardService.get(bno)); 
	}
	
	@PreAuthorize("principal.username == #board.writer") // 작성자와 로그인한 사람이 같은지 확인 
	@PostMapping("modify") 
	public String modifyPro(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		System.out.println(cri);
		boolean res = boardService.modify(board);
		if(res) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getParameterLink(); 
	}
	
	@PreAuthorize("principal.username == #writer")
	@PostMapping("delete")
	public String deletePro(@RequestParam("bno") Long bno, @RequestParam("writer") String writer, 
			Criteria cri, RedirectAttributes rttr) {
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); 
		}
		return "redirect:/board/list" + cri.getParameterLink();
	}
	

	
}
