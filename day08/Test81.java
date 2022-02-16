package day08;
// 상속
class Super2 {
	static int a = 100; // 클래스 변수
	int x = 10;
}

class Sub2 extends Super2{ // x,y
	int y = 20;
}

public class Test81 {
	public static void main(String[] args) {
		Sub2 s = new Sub2();
		System.out.println(s.x);
		System.out.println(s.y);
		System.out.println(s.toString());
		
		System.out.println();
		System.out.println(Super2.a);
		System.out.println(Sub2.a);
	}
}
