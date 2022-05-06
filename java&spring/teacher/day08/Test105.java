package day08;
class A1 {
	A1() {
		System.out.println("생성자 A");
	}
	A1(int x) {
		System.out.println("생성자 A-param");
	}
}
class B1 extends A1 {
	B1() {
		System.out.println("생성자 B");
	}
	B1(int x) {
		super(x);
		System.out.println("생성자 B-param");
	}
}


public class Test105 {
	public static void main(String[] args) {

		B1 b = new B1(10); 
		
		
	}
}
