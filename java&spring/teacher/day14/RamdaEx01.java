package day14;

public class RamdaEx01 {

	public static int sum(int x, int y) {
		return x + y; 
	}
	
	public static void main(String[] args) {
		
		MyFunctionalInterface my; 
		
		my = (x, y) -> sum(x, y);
		System.out.println(my.method(1, 2));
		
		//my = (x, y) -> { return sum(x, y); };
		
		
		/*
		my = (x, y) -> {
			int result = x + y; 
			return result;
		};
		int rs = my.method(10, 20); 
		System.out.println(rs);
		
		my = (x, y) -> { return x + y; };
		System.out.println(my.method(100, 200));
		
		my = (x, y) -> x + y; // 실행문이 하나라 중괄호 return 생략 
		System.out.println(my.method(123, 456));
		*/
		
		
		
		
		
		/* 매개변수o, 리턴x
		my = (y) -> {
			int result = y * 5; 
			System.out.println(result);
		}; 
		
		my.method(10);
		
		my = (x) ->  System.out.println( x * 10 );
		my.method(10);
		*/
		
		/* 매개변수x, 리턴x
		my = () -> {
			String str = "method 호출"; 
			System.out.println(str);
		}; 
		my.method(); 
		

		my = () -> { System.out.println("호출2"); };
		my.method();
		
		
		// 중괄호 안에 명령문이 하나라 중괄호 생략가능 
		my = () -> System.out.println("호출3"); 
		my.method();
		*/
		
	}
}









