package com.test.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component // 스프링이 관리할 객체
@ToString// toString 자동 생성
@Getter	// get메서드 자동 생성
//@Setter	// set메서드 자동 생성
//@AllArgsConstructor 	// 모든 인스턴스 변수를 생성자 매개변수로 갖는 생성자 자동 생성
//@NoArgsConstructor	// 기본 생성자 자동 생성
//@RequiredArgsConstructor	// 원하는  변수들을 매개변수로 갖는 생성자 자동 생성
							// @NonNull이나 final이 붙은 변수에 대해서만 생성자에 매개변수로 포함
public class SampleHotel {
	// 클래스 위에 붙이면 클래스 내의 모든 변수에 적용
	//변수 위에 붙이면 바로 밑의 변수 하나에만 적용.
//	@NonNull
//	@Setter
	private Chef chef;
	
	public SampleHotel(Chef chef) {
		this.chef = chef;
	}
//	private final int num;
}
