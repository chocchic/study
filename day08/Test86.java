package day08;

class Super1{
	void func() {
		System.out.println("super");
	}
	void f2() {
		System.out.println("ahahahah");
	}
}
class Sub1 extends Super1{// func() + object로 부터 받은 11개 메서드
	
	@Override // 어노테이션
	void func() {
		super.func();	// 부모의 func() 메서드 호출 
						// 이거 없으면 super클래스 거 X 오버라이딩이거든
		System.out.println("sub");
	}
	
	@Override
	void f2() {
		System.out.println("ohohohoh");
	}
}
public class Test86 {
	public static void main(String[] args) {
		Sub1 sub = new Sub1();
		sub.func();
	}
}
