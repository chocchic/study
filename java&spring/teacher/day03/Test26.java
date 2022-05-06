package day03;

import java.util.Scanner;

public class Test26 {
	public static void main(String[] args) {
		// for문 
		//문제1. 10 ~ 30 까지 출력해보세요. 
		for(int i = 10; i <= 30; i++) {
			System.out.println(i);
		}
		//문제2. 0 ~ 100 까지 10단위로 출력. 0 10 20 30...
		for(int i = 0; i <= 100; i+=10) {
			System.out.println(i);
		}
		//문제3. 1 ~ 20까지 짝수만 출력
		for(int i = 1; i <= 20; i++) {
			if(i % 2 == 0) {
				System.out.println(i);
			}
		}
		//문제4. 1 ~ 100까지 홀수의 합을 출력 
		int tot = 0; 
		for(int i = 1; i <=100; i++) {
			if(i % 2 == 1) {
				tot += i; 
			}
		}
		System.out.println("tot : " + tot);
		//문제5. 5 ~ 1 까지 역순으로 출력. 5 4 3 2 1 
		for(int i = 5; i > 0; i--) {
			System.out.println(i);
		}
		
		//문제6. 정수 1개 입력받고, 1 부터 입력받은 수까지의 합을 구하세요. 
		System.out.println("숫자 입력>>");
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		int sum = 0; 
		for(int i = 1; i <= num; i++) {
			sum += i;
		}
		System.out.println("sum : " + sum);
		
		
		sc.close();
		
		
		
	}
}
