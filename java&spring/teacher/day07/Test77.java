package day07;

// 기본생성자가 자동으로 만들어지지 않는 형태 
class Circle1 {
	int radius; 
	// 생성자 : 개발자가 의도를 가지고 생성자에 매개변수를 작성을 해놨기 때문에 
	//		외부에서 데이터를 던져주고 객체 생성을 해야 객체가 온전히 돌아가는구나~~~ 
	//		개발자의 의도를 존중하여, 기본생성자를 자동생성해주지 않아
	//		new 클래스() 이런식으로 매개변수없이 불완전할 수 있는 객체 생성을 도와주지 않는다.
	Circle1(int r) {
		radius = r;
	}
	// 매개변수 없는 생성자로도 객체 생성을 하고 싶으면
	// 개발자가 명시적으로 매개변수 없는 생성자를 직접 작성해줘야한다.
	Circle1() {}
	
	// 메서드 
	void set(int r) { // 반지름값을 세팅해주는 메서드 
		radius = r; 
	}
	double getArea() { // 원의 너비 구해주는 메서드 
		return radius * radius * 3.14; 
	}
	
}
public class Test77 {
	public static void main(String[] args) {

		//Circle1 c = new Circle1(); 불가능 
		Circle1 c = new Circle1(10);
		Circle1 c2 = new Circle1();
		
		
		
		
	}
}



