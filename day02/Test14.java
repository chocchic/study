package day02;

public class Test14 {

	public static void main(String[] args) {
		// 비교 연산자 : < <= > >= == != : 연산 결과는 true/false 둘중 하나
		
		int a = 10;
		int b = 0;
		
		System.out.println(a > b);
		//boolean result = a>b+20; // b+20이 우선 계산 되므로 false가 됨 가독성을 위해 뒤에 괄호를 치도록
		//boolean result = a >= b+10;
		boolean result = a!=b; // 둘이 다른지에 대한 대답 true -> 다르다 / false -> 같다
		System.out.println(result);
		
		// 논리 연산자 : && ||
		// A && B : A와 B 둘다 참이여야 참
		// A || B : A와 B 둘다 거짓이어야 거짓 -> 둘중 하나만 참이면 참
		result = a > b && a<5;
		System.out.println(result);
		
	}

}
