package com.test.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component	// 스프링이 관리할 객체다~ 
@ToString	// toString() 자동 생성 
//@Getter		// get메서드 자동 생성 (클래스에 붙히면 모든 변수에 적용)  
//@Setter	// set메서드 자동 생성 
//@AllArgsConstructor // 모든 인스턴스 변수를 매개변수로 갖는 생성자 자동 생성
//@NoArgsConstructor // 개본생성자 자동 생성 
//@RequiredArgsConstructor // 원하는 변수들을 매개변수로 갖는 생성자 생성 
//							@NonNull이나 final이 붙은 변수데 대해서만 생성자에 매개변수로 포함.
public class SampleHotel {

	//@NonNull
	//@Setter// set메서드 자동생성 (변수에 각각 붙히면 해당 변수에만 적용) 
	private Chef chef; 
	
	public SampleHotel(Chef chef) {
		this.chef = chef;
	}

	//private int num;
	
}
