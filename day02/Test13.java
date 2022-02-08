package day02;
import java.util.Scanner;
public class Test13 {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		// 문제1. 숫자 2개를 입력받고, 사칙 연산의 결과를 출력해보세요
		// 입력받기 --> Scanner로 입력받기
		//	Scanner 4가지 순서 --> 메모장 -->4가지 순서 적기
		System.out.println("숫자 1입력 : ");
		int num1 = sc.nextInt(); //int num1 = Integer.parseInt(sc.nextLine());
		System.out.println("숫자 2 입력 : "); 
		int num2 = sc.nextInt(); //int num2 = Integer.parseInt(sc.nextLine()); 
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / (double)num2);
		
		// 문제2. 초단위 정수를 입력받고, 몇분 몇초인지 출력해보세요
		
//		System.out.println("초를 입력하세요 : ");
//		int sec = sc.nextInt(); //int sec = Integer.parseInt(sc.nextLine());
//		int min = sec/60;
//		sec = sec%60; 
//		System.out.println(min+"분 "+sec+"초 입니다.");
		//System.out.println((sec/60)+"분 " + (sec%60)+"초");
		
		// 문제3. 초단위 정수를 입력받고, 몇시간 몇분 몇초인지 출력해보세요
		// 1분 = 60초, 1시간 = 3600초
		System.out.println("초를 입력하세요 : "); 
		int sec = sc.nextInt(); //int sec = Integer.parseInt(sc.nextLine());
		int min = sec/60;
		int hour = min/60; 
		min = min%60; 
		sec = sec%60; 
		/*
		 * int hour = sec / 3600;
		 * int min = (sec %3600)/ 60;
		 * sec = (sec%3600)%60;
		 * */
		System.out.println(hour + "시간 " + min + "분 " + sec + "초 입니다.");

		/*
		 문제4. 최소 화폐매수 구하기
		 	금액을 입력하며, 최소한의 화폐매수를 구해주는 프로그램
		 	예)
		 		금액입력 : 67800 (사용자가 숫자입력)
		 		5만원 : 1장
		 		1만원 : 1장
		 		5천원 : 1장
		 		1천원 : 2장
		 		5백원 : 1개
		 		1백원 : 3개
		 */
		
		 System.out.println("금액 입력 : "); 
		 int money = sc.nextInt(); 
		 int m5 = money / 50000; 
		 money = money %50000; //money -= m5 * 50000; 
		 int m1 = money / 10000;
		 money = money % 10000; // money -= m1*10000; 
		 int t5 = money / 5000; 
		 money = money%5000; //money -= t5*5000; 
		 int t1 = money / 1000; 
		 money = money%1000; //money -= t1*1000;
		 int h5 = money /500;
		 money = money%500; //money -= h5*500; 
		 int h1 = money /100; 
		 System.out.println("5만원 " + m5 +"장");
		 System.out.println("1만원 " + m1 +"장"); 
		 System.out.println("5천원 " + t5 +"장");
		 System.out.println("1천원 " + t1 +"장");
		 System.out.println("5백원 " + h5 +"장");
		 System.out.println("1백원 " + h1 +"장");
//		 System.out.println("5만원 : "+ (money/50000)+"장");
//		 System.out.println("1만원 : "+ ((money%50000)/10000)+"장");
//		 System.out.println("5천원 : "+ ((money%10000)/5000)+"장");
//		 System.out.println("1천원 : "+ (money%5000)+"장");
//		 System.out.println("5벡원 : "+ ((money%1000)/500)+"장");
//		 System.out.println("1백원 : "+ ((money%500)/100)+"장");

		 sc.close(); // 한번 닫으면 다시 객체 생성해도 사용불가
		}
}
