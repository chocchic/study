package day08;
// 상속 
class Super {
	static int a = 100; // 클래스변수 
	int x = 10; 
}
class Sub extends Super { // x, y 
	int y = 20; 
}

public class Test101 {
	public static void main(String[] args) {

		Sub s = new Sub(); 
		System.out.println(s.x);
		System.out.println(s.y);
		System.out.println(s);
		System.out.println(s.toString());
		
		System.out.println(Super.a);
		System.out.println(Sub.a);
		
		
		
	}
}
