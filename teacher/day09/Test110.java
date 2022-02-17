package day09;

class A { int x; }	// x
class B extends A { int y; } // x, y

public class Test110 {
	public static void main(String[] args) {

		// 기존 개게 생성 방식 
		A a = new A(); 
		B b = new B(); 
		
		// 다형성 : 조상의 참조변수로 자식의 객체를 생성하는것 
		A a1 = new B(); 
		System.out.println(a1.x);
		
		// 부모객체가 자식변수에 들어가는건 안된다. 
		//B b1 = new A(); 
		
		
		
	}
}
