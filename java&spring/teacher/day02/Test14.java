package day02;

public class Test14 {
	public static void main(String[] args) {

		// 비교연산자 : < <= > >= == != : 연산 결과는 true/false 둘중 하나
		int a = 10; 
		int b = 0; 
		
		//boolean result  = a > b + 20; 
		//boolean result  = a >= b + 10; 
		boolean result  = a != b; 
		System.out.println(result);
		
		// 논리연산자 : && ||
		// A && B : A와 B 둘다 참이여야 참.  
		// A || B : A와 B 둘다 거짓이여야 거짓. 
		
		//result = a > b && a < 5; 
		
		result = a == 20 || a > b && a < 5; 
		System.out.println(result);
		
		// 삼항연산자 : 조건식 ? 참일때값 : 거짓일때값;  
		int res = a > b ? a : b;
		System.out.println("res : " + res);
		char resch = a < b ? 'O' : 'X';
		System.out.println(resch);
		System.out.println(a > b ? a : b);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
