package day07;

class Circle{
	int radius; // 반지름
	// 자동으로 기본 생성자 생성되는 형태
	//Circle(){} // 눈에 안보이는 생성자 생성되어 실행하며 객체 생성
	
	void set(int r) { // 반지름값을 세팅해주는 메서드 
		radius = r;
	}
	
	double getArea() { // 원의 너비 구해주는 메서드
		return radius * radius * 3.14;
	}
}

public class Test76 {
	Circle c = new Circle(); // 생성자에 매개변수를 받도록 해놓으면 없이 객체 생성 X
}
