package day08;

class A {
	A() {
		System.out.println("생성자A");
	}
}
class B extends A {
	B() {
		System.out.println("생성자B");
	}
}
class C extends B {
	C() {
		System.out.println("생성자C");
	}
}

public class Test104 {
	public static void main(String[] args) {

		C c;
		c = new C(); 
		
		
	}
}
