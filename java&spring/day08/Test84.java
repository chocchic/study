package day08;
class A{
	A(){
		System.out.println("생성자 A");
	}
}

class B extends A{
	B(){
		System.out.println("생성자 B");
	}
}
class C extends B{
	C(){
		System.out.println("생성자 C");
	}
}

public class Test84 {
	public static void main(String[] args) {
		C c = new C(); //A B C 순으로 출력
		
	}
}
