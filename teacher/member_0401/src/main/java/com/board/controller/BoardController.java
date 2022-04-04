package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.board.mapper.BoardMapper;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {

	@Autowired
	private BoardService boardService; 
	@Autowired
	private BoardMapper boardMapper; 
	
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
	
	@GetMapping("list") // list 요청시 pageNum, listQty 가 들어와야함.
	public void list(Criteria cri, Model model) {
		//model.addAttribute("list", boardService.getList());

		// 페이징처리 버전!! 
		// 현재 페이지 번호에 따른, 보여줄 글 목록 가져와 view에 전달 
		model.addAttribute("list", boardService.getList(cri));
		
		// 전체 글의 개수 가져오고 
		int total = boardService.getTotal(cri); 
		// 화면에 띄워줄 페이지번호등 계산완료된 PageDTO 객체도 view전달 
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	@GetMapping({"content", "modify", "delete"})
	public void content(@RequestParam("bno") Long bno, @ModelAttribute("cri")Criteria cri, Model model) {
		model.addAttribute("board", boardService.get(bno)); 
	}
	
	@PostMapping("modify") 
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		System.out.println(cri);
		boolean res = boardService.modify(board);
		if(res) {
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getParameterLink(); 
	}
	@PostMapping("delete")
	public String delete(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
		
		if(boardService.remove(bno)) {
			rttr.addFlashAttribute("result", "success"); 
		}
		
		return "redirect:/board/list" + cri.getParameterLink();
	}
	
	@GetMapping("test")
	public void test() {
		
		Criteria cri = new Criteria(); 
		cri.setType("TC");
		cri.setKeyword("w");
		
		List<BoardVO> list = boardMapper.getListWithPaging(cri); 
		list.forEach(vo -> System.out.println(vo));
		
	}
	
	
	
	
	
	

	
}
