package day02;
import java.util.Scanner;
public class Test18 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		// *연산자 문제*
//		// 문제 1. 정수 두개를 입력받고, 몫과 나머지 출력
		System.out.print("정수 두개를 입력하세요 : ");
		int num1_1 = sc.nextInt();
		int num1_2 = sc.nextInt();
		System.out.println("몫 : " + (num1_1/num1_2) + " 나머지 : " + (num1_1%num1_2));

		// 문제 2. 일수(day)를 입력받고, 몇개월 몇일인지 출력 단, 한달은 30일 고정
		System.out.print("일수를 입력하세요 : ");
		int day = sc.nextInt();
		System.out.println((day/30)+"개월 " + (day%30) + "일");
				
		// 문제 3. 점수 3개를 입력받고, 총점과 평균 출력
		System.out.print("정수 3개를 입력하세요 : ");
		int num3_1 = sc.nextInt();
		int num3_2 = sc.nextInt();
		int num3_3 = sc.nextInt();
		System.out.println("총점 : " + (num3_1+ num3_2+num3_3) + " 평균 : " + ((num3_1+ num3_2+num3_3)/3.0) );
		/*
		System.out.println("점수1 입력 : ");
		int sco1 = Integer.parseInt(sc.nextLine());
		System.out.println("점수2 입력 : ");
		int sco2 = Integer.parseInt(sc.nextLine());
		System.out.println("점수3 입력 : ");
		int sco3 = Integer.parseInt(sc.nextLine());
		int total = sco1+sco2+sco3;
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + total/3.0);
		*/
		
		// *if문 문제*
		// 문제 4. 숫자 하나를 입력받고, "음수"인지 "양수"인지 출력
		System.out.print("숫자를 하나 입력하세요 : ");
		int num4 = sc.nextInt();
		if(num4 > 0) {
			System.out.println("양수");
		}else if (num4 == 0){
			System.out.println("0");
		}else {
			System.out.println("음수");
		}
		
		/*
		if(num4 > 0) {
			System.out.println("양수");
		}else if(num4 < 0) {
			System.out.println("음수");
		}else{
			System.out.println("0");
		}
		*/
		
		// 문제 5. 1 ~ 99 사이 숫자를 입력받고, "짝수"인지 "홀수"인지 출력
		System.out.print("1~99 사이 수를 입력하세요 : ");
		int num5 = sc.nextInt();
		if(num5%2==0) {
			System.out.println("짝수");
		}else {
			System.out.println("홀수");
		}

		// 문제 6. 정수 3개를 입력받고, 가장 큰 수를 출력
		System.out.print("정수 3개를 입력하세요 : ");
		int num6_1 = sc.nextInt();
		int num6_2 = sc.nextInt();
		int num6_3 = sc.nextInt();
		
		if(num6_1 > num6_2) {
			if(num6_1 > num6_3) {
				System.out.println("가장 큰 수는 "+ num6_1);
			}else {
				System.out.println("가장 큰 수는 "+ num6_3);
			}
		}else {
			if(num6_2 > num6_3) {
				System.out.println("가장 큰 수는 "+ num6_2);
			}else {
				System.out.println("가장 큰 수는 "+ num6_3);
			}
		}
		
		// 문제 7. 2차원 평면에서 직사각형은 왼쪽모서리와 오른쪽 하단 모서리 두점으로 표현한다. 
		// 		  왼쪽위가 0,0
		// 		  (100,100)과 (200,200)의 두 점으로 이루어진 사각형이 있을 때,
		// 		  Scanner를 이용해 정수 x,y값을 입력받고, 점(x,y)가 이 직사각형 안에 있는지 판단하는 프로그램을 만드세요.
		//	x의 범위는 100~200, y의 범위도 100~200
		System.out.print("x값과 y값을 입력하세요 : ");
		int num7_1 = sc.nextInt();
		int num7_2 = sc.nextInt();
		
		if((200>=num7_1)&&(num7_1>=100)&&(200>=num7_2)&&(num7_2>=100)){
			System.out.println("직사각형 안에 존재합니다.");
		}else {
			System.out.println("직사각형 밖에 있습니다.");
		}
		
		// * 추가 문제*
		// 문제 8. 368게임. 1~99까지 정수를 입력받고, 정수에 3,6,9중에 하나가 있는 경우 "박수짝을 입력.
		// 		  두 개있는 경우 "박수짝짝"을 출력.
		// String str = "abc";
		// char ch = str.charAt(0); // ch = a
		// ch = str.charAt(2); //ch = c
//		System.out.print("1~99 정수를 하나 입력하세요 : ");
//		String num8 = sc.nextLine();
////		int n = Integer.parseInt(num8);
////		if(n > 10) {
//		if(num8.length() > 1) { // 문자열.length()는 그 문자열의 길이를 알아내는 메소드 
//			char ch1 = num8.charAt(0);
//			char ch2 = num8.charAt(1);
//			if((ch1 == '3') ||(ch1 == '6')||(ch1 == '9')) {
//				if((ch2 =='3') ||(ch2 == '6')||(ch2 == '9')) {
//					System.out.println("박수짝짝");
//				}else {
//					System.out.println("박수짝");
//				}
//			}else{
//				if((ch2 == '3') ||(ch2 == '6')||(ch2 == '9')) {
//					System.out.println("박수짝");
//				}	
//			}
//		}else {
//			char ch1 = num8.charAt(0);
//			if((ch1 == '3') ||(ch1 == '6')||(ch1 == '9')) {
//				System.out.println("박수짝");
//			}
//		}		
		
		// switch문제 
	    // 문제9. 정수를 입력받아 3~5는 "봄", 
	    //      6~8은 "여름", 9~11은 "가을", 12~2는 "겨울을 출력.
	    //      그 외 다른수는 "잘못입력" 출력. 
//		System.out.print("몇 월인지 입력하세요 : ");
//		int num9 = sc.nextInt();
//		
//		switch(num9) {
//		case 3: case 4: case 5:
//			System.out.println("봄");break;
//		case 6: case 7: case 8:
//			System.out.println("여름");break;
//		case 9: case 10: case 11:
//			System.out.println("가을");break;
//		case 12: case 1: case 2:
//			System.out.println("겨울");break;
//		default:
//			System.out.println("잘못입력");
//		}
		
		// 문제10. 성적을 입력받고 90~100 "수", 80~89 "우", 
	    //      70~79 "미", 60~69 "양", 그 이하는 "가" 출력
//		System.out.print("1~100 점수를 하나 입력하세요 : ");
//		int num10 = sc.nextInt();
//		switch(num10) {
//		case 100: case 99: case 98: case 97: case 96: case 95: case 94: case 93: case 92: case 91: case 90:
//			System.out.println("수"); break;
//		case 89: case 88: case 87: case 86: case 85: case 84: case 83: case 82: case 81: case 80:
//			System.out.println("우"); break;
//		case 79: case 78: case 77: case 76: case 75: case 74: case 73: case 72: case 71: case 70: 
//			System.out.println("미"); break;
//		case 69: case 68: case 67: case 66: case 65: case 64: case 63: case 62: case 61: case 60: 
//			System.out.println("양"); break;
//		default:
//			System.out.println("가"); break;
//		}
//		
//		if(num>=90) {
//			System.out.println("수");
//		}else if(num>=80) {
//			System.out.println("우");
//		}else if(num>=70) {
//			System.out.println("미");
//		}else if(num>=60) {
//			System.out.println("양");
//		}else {
//			System.out.println("가");		
//		}
	      // 문제11. 숫자 두개와 사칙연산기호중 하나를 입력받아,
	      //      입력받은 값들의 사칙연산 결과를 출력하세요.
		System.out.print("숫자 2개와 사칙연산 기호하나 를 입력하세요 : ");
		int num11_1 = Integer.parseInt(sc.nextLine());
		int num11_2 = Integer.parseInt(sc.nextLine());
		String cal = sc.nextLine();
		System.out.println(num11_1+" " + cal + " " + num11_2);
		switch(cal) {
		case "+":
			System.out.println(num11_1+num11_2); break;
		case "-":
			System.out.println(num11_1-num11_2); break;
		case "/":
			System.out.println(num11_1/num11_2); break;
		case "*":
			System.out.println(num11_1*num11_2); break;
		}
	}

}
