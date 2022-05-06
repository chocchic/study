package day10;
// 외부 클래스
class A {
	A() {System.out.println("A 객체 생성");}
	// 인스턴스 멤버 클래스
	class B{
		B() {System.out.println("B 객체 생성"); }
		int x;
		void func1() { System.out.println("B 클래스 메서드 실행");}
	}
	// static 멤버 클래스
	static class C{
		C() {System.out.println("C 객체 생성");}
		int c;
		void func2() {
			System.out.println("C 클래스 메서드 실행");
		}
		static int d;
		static void func3() {
			System.out.println("C 클래스 static 메서드 실행");
		}
	}
	void methodA() {
		// 로컬 클래스
		class D {
			D() { System.out.println("D 객체 생성"); }
			int x;
			void func() { System.out.println("D 클래스 메서드 실행"); }
		}
		D d = new D();
		d.x = 200;
		System.out.println("d.x : " + d.x);
		d.func();
	}
	
}
public class Inner01 {
	public static void main(String[] args) {
		// 인스턴스 B 사용
		A a = new A();
		A.B b = a.new B();
		System.out.println(b.x);
		
		// static 멤버 클래스 C 생성
		A.C c = new A.C();
		System.out.println("c.c : " + c.c);
		c.c=100;
		System.out.println("c.c : " + c.c);
		A.C.d = 200;
		A.C.func3();
		
		A a1 = new A();
		a1.methodA();
	}
}
