package day09;
class A {int x;} // y
class B extends A { int y; } // x
public class Test90 {
	public static void main(String[] args) {
		// 기존 개체 생성 방식
		A a = new A();
		B b = new B();
		
		// 다형성 : 조상의 참조변수로 자식의 객체를 생성하는 것
		A a1 = new B(); // y객체가 실제로 heap메모리에 있지만 부르지 못하는거라 기능에 상관 X
		System.out.println(a1.x);
		
		// 부모객체가 자식변수에 들어가는 건 안된다
//		B b1 = new A(); // 불가능. 메모리상에 아예 y가 존재하지 않기 때문에 문제 발생 
	}
}
