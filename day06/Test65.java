package day06;

import java.util.Scanner;

// 재귀호출 : factorial : 1부터 입력받은 수 n까지 곱한 결과를 돌려주는 메서드
public class Test65 {
	
	static long factorial(int n) {
		if(n == 1) {
			return 1;
		}else {
			System.out.println("else : "+ n );
			return n * factorial(n-1);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 입력 >> ");
		int n = Integer.parseInt(sc.nextLine());
		long result = factorial(n);
		System.out.println(result);
		
		sc.close();
	}
}
