package day05;

import day04.Test40;

class Tv6{
	static int x = 100;
	int a = 10;
}

public class Test58 {
	static int a = 10;
	
	public static void main(String[] args) {
		int a = 200; 
		System.out.println(a); // 지역변수 a 호출
		System.out.println(Test58.a); // 클래스 변수 a 호출
		// 변수 우선순위 : 지역 > 인스턴스 > 클래스
		
		/*
			자바 JVM이 5개 영역으로 나눠서 사용
			Class Area(Method Area) : static이 붙은 아이들이 저장되는 공간
			Stack : 지역변수 
			Heap : new~~ 생성된 객체
			Native Method Stack Area : C언어와 호환할 때 사용하는 영역. C 관련 데이터 저장
			PCregister 
		*/
		
		Tv6 t = new Tv6();
		System.out.println(t.a);
		Test56 t1 = new Test56();
		Test40 tt = new Test40(); // day04의 class이므로 import
		
	}
}
