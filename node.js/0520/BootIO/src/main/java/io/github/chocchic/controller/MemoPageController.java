package io.github.chocchic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.chocchic.dto.MemoDTO;
import io.github.chocchic.dto.PageRequestDTO;
import io.github.chocchic.service.MemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
// 로그 기록을 편리하게 할 수 있도록 해주는 어노테이션
@Log4j2
// 인스턴스 변수의 주입을 생성자에서 자동으로 처리하도록 해주는 어노테이션
@RequiredArgsConstructor
public class MemoPageController {
	private final MemoService m;
	
	// 요청이 오면 templates 디렉터리에 있는 main.html을 출력
	@GetMapping("/")
	public String main() {
		// redirect할 때는 View의 이름을 적는 것이 아니고 요청을 적어야합니다.
		return "redirect:/memo/list";
	}
	
	// 목록보기 요청을 처리
	@GetMapping("/memo/list")
	public void list(PageRequestDTO pr, Model model) {
		log.info("목록보기");
		model.addAttribute("result", m.getList(pr));
	}
	
	// 삽입화면으로 이동하는 요청을 처리
	@GetMapping("/memo/register")
	public void register() {
		log.info("데이터 삽입화면으로 이동");
	}
	
	// 데이터 삽입 처리
	@PostMapping("/memo/register")
	public String register(MemoDTO dto, RedirectAttributes rAttr) {
		// 여기가 제데로 출력이 안되면 요청 URL과 View이름을 확인하고 form의 경우라면 입력요소의 name을 확인
		log.info("데이터 삽입 요청 : ", dto);
		// 삽입
		Long gno = m.insertMemo(dto);
		//model에 msg 저장 -> 세션도 모델도 redirect되면 남아있지 않음
		//model.addAttribute("msg",gno+"로 저장완료!");
		//session.setAttribute("msg",gno+"로 저장완료!");
		// 리다이렉트할 때 데이터를 전달
		rAttr.addFlashAttribute("msg",gno+"로 저장완료!");
		return "redirect:/memo/list";
	}
	
	@GetMapping({"/memo/detail", "/memo/update"})
	public void detail(Long gno, @ModelAttribute("requestDTO")PageRequestDTO pr, Model model) {
		MemoDTO memo = m.read(gno);
		model.addAttribute("memo",memo);
	}
	
	// 데이터 수정 처리
	@PostMapping("/memo/update")
	public String update(MemoDTO dto, @ModelAttribute("requestDTO")PageRequestDTO pr, RedirectAttributes rAttr) {
		log.info("dto : "+ dto);
		m.modify(dto);
		// 상세보기로 이동할 때 필요한 gno값과 page값을 설정
		rAttr.addAttribute("page",pr.getPage());
		rAttr.addAttribute("gno",dto.getGno());
		return "redirect:/memo/detail";
	}
	// 데이터 수정 처리
	@PostMapping("/memo/delete")
	public String delete(Long gno, @ModelAttribute("requestDTO")PageRequestDTO pr, RedirectAttributes rAttr) {
		m.remove(gno);
		rAttr.addAttribute("page",pr.getPage());
		return "redirect:/memo/list";
	}
}
