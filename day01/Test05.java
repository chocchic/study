package day01;

public class Test05 {

	public static void main(String[] args) {
		
		// 문제 1. 정수형 변수 a, b에 각각 10, 20을 대입하고, 값을 서로 교환해보세요
		int a = 10, b= 20;
		System.out.println(a+" "+b); //10 20
		
		// 값 교환 코드
		int temp;
		temp = b;
		b = a;
		a = temp;
		
		System.out.println(a+" "+b); // 20 10
		
		
	}

}
