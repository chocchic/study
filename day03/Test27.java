package day03;

import java.util.Scanner;

public class Test27 {
	public static void main(String[] args) {
		
		// 중첩 반복, 2중 for문
		for(int i = 0; i<=3; i++) {
			for(int j = 0; j <= 3; j++) {
				System.out.println(i + " "+ j);
			}
		}
		
		// 문제 1. 구구단 2단 출력
		for(int i = 1; i<=9;i++) {
			System.out.println("2 * " + i + " = " + (2*i));
		}
		// 문제 2. 구구단의 단수를 입력받아, 해당 단을 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("단수 입력 : ");
		int num = Integer.parseInt(sc.nextLine());
		for(int i = 1; i< 10; i++) {
			System.out.println(num + " * " + i + " = " + (num*i));
		}
		// 문제 3. 구구단 전체 출력
		for(int i = 2; i < 10;i++) {
			System.out.println("  - " + i+"단 -  ");
			for(int j = 1; j < 10; j++) {
				System.out.println(i + " * " + j + " = " + (i*j));
			}
		}
		// 문제 3-1. 1단 2 단 3단 단별로 세로 정렬
		for(int i = 1; i < 10;i+=3) {
			System.out.println("    "+i+"단\t\t    "+(i+1)+"단\t\t    "+(i+2)+"단");
			for (int j = 1; j<10;j++) {
				System.out.print(i + " * " + j + " = " + (i*j)+"\t");
				System.out.print((i+1) + " * " + j + " = " + ((i+1)*j)+"\t");
				System.out.println((i+2) + " * " + j + " = " + ((i+2)*j));
			}
			
		}
		// 문제 4. 별찍기
		for(int i = 1; i<5;i++) {
			for(int j = i; j>0;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
