package day04;

public class Test47 {
	public static void main(String[] args) {

		// 랜덤 : 난수 : 임의의 수 
		// java.lang.Math : 임포트 필요 없이 바로 사용 가능. 
		System.out.println(Math.random());
		
		// 0 ~ 9 값 
		for(int i = 0; i < 10; i++) {
			System.out.println( (int)(Math.random() * 10) );
		}
		
		
		// 문제1. 1 ~ 10 사이 랜덤값 구해서 출력
		System.out.println((int)(Math.random() * 10) + 1);
		
		// 문제1-1. 0 ~ 8사이 랜덤값 구해서 출력
		System.out.println((int)(Math.random() * 9));
		
		// 문제2. 13 ~ 28 사이 랜덤값 구해서 출력
		System.out.println((int)(Math.random() * 16) + 13);
				
		
		
		
		
		
	}
}
