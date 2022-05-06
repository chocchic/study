package day10;

import java.util.Scanner;

public class TestEx04 {
	public static void main(String[] args) {
		// 3개의 정수를 입력받아 합을 구하는 프로그램
		Scanner sc = new Scanner(System.in);
		System.out.println("정수 3개 입력하세요 ... ");
		int sum = 0, n = 0;
		for(int i = 0; i< 3; i++) {
			System.out.println(i + ">>");
			try {
			n = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("정수가 아닙니다. 다시 입력하세요");
				i--;
				continue;
			}
			sum += n;
		}
		System.out.println("합은 : " + sum);
		sc.close();
	}
}
