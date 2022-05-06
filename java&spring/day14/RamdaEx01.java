package day14;

public class RamdaEx01 {
	public static int sum(int x, int y) {
		return x+y;
	}
	public static void main(String[] args) {
		MyFuntionalInterface my;
//		my = () -> {
//			String str = "method 호출";
//			System.out.println(str);
//		};
//		my.method();
//		
//		my = () -> { System.out.println("호출 2"); }; // 덮어쓰기 됨
//		my.method();
//		
//		// 중괄호 안에 명령문이 하나라 중괄호 생략가능
//		my = () -> System.out.println("호출 3");
//		my.method();
		
		// 인터페이스에 매개변수를 하나 추가, 리턴 X
		/*
		my = (x) -> {int result = x * 5; System.out.println(result);};
		my.method(10);
		
		my = (x) -> {System.out.println(x*10);};
		my.method(10);
		
		my = (x) -> System.out.println(x*15);
		my.method(10);
		*/
		// 매개변수가 2개이고 리턴 O
		/*
		my = (x,y) -> { int result = x+y; return result;};
		int rs  = my.method(10, 20);
		System.out.println(rs);
		
		my = (x,y) -> { return x+y;};
		System.out.println(my.method(100, 200));
		
		my = (x,y) -> x+y;
		System.out.println(my.method(123, 456));
		*/
		//매개변수를 2개받고, 미리 선언한 함수 호출
		my = (x,y)->sum(x,y); // => my = (x,y)->{return sum(x,y)};
		System.out.println(my.method(10, 20));
	}
}
