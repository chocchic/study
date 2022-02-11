package day02;
import java.util.Scanner;

public class Test18 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// *연산자문제*
		// 문제1. 정수 두개를 입력받고, 몫과 나머지를 출력하세요.
//		System.out.println("입력1:");
//		int num1 = Integer.parseInt(sc.nextLine());
//		System.out.println("입력2:");
//		int num2 = Integer.parseInt(sc.nextLine());
//		System.out.println("몫 : " + (num1 / num2) + " 나머지 : " + (num1 % num2));
		// 문제2. 일수(day)를 입력받고, 몇개월 몇일인지 출력
		//			단, 한달을 30일 고정 
		// 문제3. 점수 3개를 입력받고, 총점과 평균을 출력하세요. 
//		System.out.println("점수1 입력:");
//		int sco1 = Integer.parseInt(sc.nextLine());
//		System.out.println("점수2 입력:");
//		int sco2 = Integer.parseInt(sc.nextLine());
//		System.out.println("점수3 입력:");
//		int sco3 = Integer.parseInt(sc.nextLine());
//		
//		int total = sco1 + sco2 + sco3; // 총점
//		System.out.println("총점 : " + total);
//		System.out.println("평균 : " + (total / 3.0));
		
		// *if문 문제*
		// 문제4. 숫자 하나를 입력받고, "음수"인지 "양수"인지 출력하세요. 
//		System.out.println("숫자 입력>>");
//		int num = Integer.parseInt(sc.nextLine());
//		if(num > 0) {
//			System.out.println("양수");
//		}else if(num < 0) {
//			System.out.println("음수");
//		}else {
//			System.out.println("0입니다.");
//		}
		// 문제5. 1 ~ 99 사이 숫자를 입력받고, "짝수"인지 "홀수"인지 출력해보세요. 
//		System.out.println("1 ~ 99 사이 숫자 입력>>");
//		int num = Integer.parseInt(sc.nextLine());
//		if(num % 2 == 0) {
//			System.out.println("짝수");
//		}else {
//			System.out.println("홀수");
//		}
		
		// 문제6. 정수 3개를 입력받고, 가장 큰수를 출력하세요. 
		System.out.println("입력1:");
		int num1 = Integer.parseInt(sc.nextLine());
		System.out.println("입력2:");
		int num2 = Integer.parseInt(sc.nextLine());
		System.out.println("입력3:");
		int num3 = Integer.parseInt(sc.nextLine());
		
		if(num1 > num2) {
			if(num1 > num3) {
				System.out.println("가장큰수: " + num1);
			}else {
				System.out.println("가장큰수: " + num3);
			}
		}else {
			if(num2 > num3) {
				System.out.println("가장큰수: " + num2);
			}else {
				System.out.println("가장큰수: " + num3);
			}
		}

		// 문제7. 2차원 평면에서 직사각형은 왼쪽 모서리와 오른쪽 하단 모서리 
		//		두점으로 표현한다. (왼쪽 위가 0,0) 
		//		(100,100)과 (200,200)의 두점으로 이루어진 사각형이 있을때, 
		//		Scanner를 이용해 정수 x,y 값을 입력받고,
		//		점(x,y)가 이 직사각형 안에 있는지 판단하는 프로그램을 만드세요. 
		
		// * 추가문제 *
		// if문제 
		// 문제8. 369게임. 1~99 까지 정수를 입력받고, 
		//		정수에 3,6,9중 하나가 있는 경우 "박수짝"을 출력 
		//		두개 있는 경우 "박수짝짝" 을 출력해보세요. 
		// 힌트1. 
		// String str = "abc"; 
		// char ch = str.charAt(0); ch ==> a 
		// ch = str.charAt(2); ch ==> c
		// 힌트2. 
		// int len = str.length(); len ==> 3
		
		// switch문제 
		// 문제9. 정수를 입력받아 3~5는 "봄", 
		//		6~8은 "여름", 9~11은 "가을", 12~2는 "겨울을 출력.
		//		그 외 다른수는 "잘못입력" 출력. 
		
		// 문제10. 성적을 입력받고 90~100 "수", 80~89 "우", 
		//		70~79 "미", 60~69 "양", 그 이하는 "가" 출력 
		
		// 문제11. 숫자 두개와 사칙연산기호중 하나를 입력받아,
		//		입력받은 값들의 사칙연산 결과를 출력하세요. 
		
		
		
		
		
		
		
		
		
		sc.close();
		
		
	}
}
