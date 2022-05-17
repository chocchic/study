package kr.co.adamsoft;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//기본 패키지 안에 있으면 인스턴스를 생성 -  @Controller, RestController, Servcie, Repository, Component
//Controller 와 RestController는 매칭되는 URL이 있으면 메서드를 호출
//Controller 와 RestController는 서블릿이 됩니다.
//Controller 는 view 이름을 이용해서 출력을 만들고 
//RescController는 문자열이나 json 문자열을 출력
@RestController
public class CommonController {
	@GetMapping("/")
	public String home() {
		return "Hello Spring Boot";
	}
}
