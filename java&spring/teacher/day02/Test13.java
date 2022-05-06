package day02;
import java.util.Scanner;

public class Test13 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		/*
		//문제1. 숫자 2개를 입력받고, 사칙연산의 결과를 출력해보세요.
		// 입력받기 --> Scanner 로 입력받기 
		//			Scanner 4가지 순서 --> 메모장 --> 4가지 순서 적기 
		//			1개부터입력받아보고 잘됐는지 실행해서 확인 
		//			복사해서 다른이름로 하나더 받아서 실행 확인
		// 입력받은 숫자 두개를 사칙연산 결과로 출력 -> + - * / 
		System.out.println("숫자 입력1:");
		int num1 = Integer.parseInt(sc.nextLine());
		System.out.println("숫자 입력2:");
		int num2 = Integer.parseInt(sc.nextLine());
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / (double)num2);
		
		
		//문제2. 초단위 정수를 입력받고, 몇분,몇초 인지 출력해보세요. 
		System.out.println("초단위의 정수 입력:");
		int input = Integer.parseInt(sc.nextLine());
		System.out.println((input / 60) + "분" + (input % 60) + "초");
		
		//문제3. 초단위 정수를 입력받고, 몇시간, 몇분, 몇초인지 출력해보세요. 
		System.out.println("초단위의 정수 입력:");
		int input = Integer.parseInt(sc.nextLine());
		// 1분 = 60초 
		// 1시간 = 60분 = 3600초 
		int hour = input / 3600; 
		int minutes = (input % 3600) / 60;  
		int sec = (input % 3600) % 60; 
		System.out.println(hour + "시간 " + minutes + "분 " + sec + "초");
		*/
		/*
		//문제4. 최소화폐매수 구하기 
		 	금액을 입력하며, 최소한의 화폐매수를 구해주는 프로그램. 
		 	콘솔예) 
		 		금액입력 :   
		 		67800 (사용자가 숫자입력) 
		 		5만원 : 1장 
		 		1만원 : 1장
		 		5천원 : 1장 
		 		1천원 : 2장
		 		5백원 : 1개 
		 		1백원 : 3개 
		*/
		System.out.println("금액 입력:");
		int money = Integer.parseInt(sc.nextLine());
		System.out.println("5만원 : " + (money / 50000) + "장");
		System.out.println("1만원 : " + (money % 50000 / 10000) + "장");
		System.out.println("5천원 : " + ((money % 10000) / 5000) + "장");
		System.out.println("1천원 : " + ((money % 5000) / 1000) + "장");
		System.out.println("5백원 : " + ((money % 1000) / 500) + "개");
		System.out.println("1백원 : " + ((money % 500) / 100) + "개");
		
		
		
		
		
		
		
		
		sc.close(); // 한번닫으면 다시 객체생성해도 사용불가 
		
	}
}
