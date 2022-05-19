package io.github.chocchic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.chocchic.domain.PersonVO;

@Controller
public class PageController {
	/*
	// /가 GET 방식으로 요청된 경우 처리
	@GetMapping("/")
	public String main(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("language", "Java");
		map.put("database","MySQL");
		map.put("framework","SpringBoot");
		map.put("IDE","sts");
		
		List<String> task = new ArrayList<>();
		task.add("Developer");
		task.add("Operator");
		task.add("DBA");
		task.add("DevOps");
		task.add("MLOps");
		
		model.addAttribute("map",map);
		model.addAttribute("list",task);
		
		// 여기서 리턴하는 문자열은 view의 이름
		return "main";
	}
	*/
	@GetMapping("/")
	public String main(Model model) {
		// Scala Data 생성
		String name = "chocchic";
		
		// 개인 취향 차. new로 해도 됨
		// 하나의 Instance 생성
		PersonVO person = PersonVO.builder().num(1L).name(name).nickname("촉촉한초코칩").birthTime(LocalDateTime.now()).build();
		// 여러개 데이터 생성
		// 1부터 20까지의 정수 스트림을 생성해서 LongStream으로 변환한 후
		// map(변환)ToObj(게겣로 변환 - 매개변수가 1개이고 1개의 객체를 리턴하는 람다를 매개변수로 사용)
		// 람다의 매개변수는 스트림의 데이터가 순서대로 들어오고 리턴하는 객체들을 모아서 list로 리턴
		List<PersonVO> list = IntStream.rangeClosed(1, 20).asLongStream().mapToObj(i -> {
			PersonVO temp = PersonVO.builder().num(i).name(name+"_"+i).nickname("촉촉한초코칩_"+i).birthTime(LocalDateTime.now()).build();
			return temp;
		}).collect(Collectors.toList());
		
		// 생성한 데이터를 request 객체에 저장
		model.addAttribute("name", name);
		model.addAttribute("person", person);
		model.addAttribute("list", list);
		
		// 여기서 리턴하는 문자열은 view의 이름입니다.
		return "main";
	}
	
	// exformat 요청이 GET 방식으로 오면 
	@GetMapping("/exformat")
	public String exformat(Model model, RedirectAttributes rattr, HttpSession session, HttpServletRequest request) {
		// 전송할 데이터를 생성 - Service의 메서드를 호출해서 생성하는 경우가 대부분
		List<PersonVO> list = new ArrayList<>();
		for(long i = 1; i<=10;i++) {
			PersonVO person = PersonVO.builder().num(i).name("이름_"+i).nickname("별명_"+i).birthTime(LocalDateTime.now()).build();
			list.add(person);
		}
		// 데이터를 저장
		// model이나 request에 저자하면 포워딩하는 경우에는 데이터가 전달됩ㄴ디ㅏ.
		// redirect하면 model이난 request에 저장된 데이터는 소멸됩니다.
		
		// request할 때도 데이터를 전달하고자 하는 경우에는 session이나 rattr을 이용
		// session은 session에서 강제로 삭제하지 않는한 유지되지만 rattr은 한번만 유지됩니다.
		
		model.addAttribute("list", list);
		//request.setAttribute("list", list);
		
		// 위 두개는 같은 의미, 세션에 하면 나중에도 써먹을 수 있다는 차이점이 있다.
		// 아래 코드는 redirect할 때 사용하면 됩니다.
		//session.setAttribute("list", list);
		//rattr.addFlashAttribute("list", list);
		
		// templates 디렉터리의 exformat.html 파일로 출력
		return "exformat";
	}
	
	@GetMapping("/exlayout")
	//리턴이 없으면 요청 URL이 view이름(exlayout)이 됩니다.
	public void exlayout() {
		
	}
}
