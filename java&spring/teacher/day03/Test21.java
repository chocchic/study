package day03;

import java.util.Scanner;

public class Test21 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 무한반복해서 숫자를 입력받고 출력하되 3을 입력하면 종료! 
		while(true) {
			System.out.print("정수입력>>");
			int num = Integer.parseInt(sc.nextLine());
			System.out.println(num);
			if(num == 3) { // 종료 시점 
				break;
			}
		}

		System.out.println("프로그램 종료!!");
		
		
		
		
		
		
	}
}
