package io.github.chocchic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
