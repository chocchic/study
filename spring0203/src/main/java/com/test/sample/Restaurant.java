package com.test.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component // 스프링이 관리하 객체 <context:component-scan>
@Data	// Restaurant의 getter/setter/toString/생성자 등 다 자동 생성
public class Restaurant {
	/*
	@Setter : 자동으로 setChef()를 컴파일시 생성함
	onMethod_ : 생성되는 setChef()에 @Autowired 어노테이션을 추가
				setChef(Chef) 메서드에 매개변수로 있는 chef를 자동으로 주입해줌
	*/
//  방법 1. 	@Autowired
//	방법 2. 	@Setter(onMethod_=@Autowired)
//  방법 3.	xml에서 <bean>으로 이 클래스 생성할 때, <property> 태그로 set메서드 호출하여 채워주는 형태로 작성
	//		component-scan하면 오류나니깐 조심~
	@Autowired
	private Chef chef;
	
	// Restaurant rt = new Restaurant(); --> 스프링이 객체 생성하도록 해줌
	// rt.setChef(스프링이 객체 생성해둔 Chef 객체를 자동으로 매개변수 값으로 채우기)
}
