package day03;

import java.util.Scanner;

public class Test22 {
	public static void main(String[] args) {
		/*
		//문제1. 0 ~ 10 까지 출력해보세요. 
		int i = 0; 
		while(i <= 10) {
			System.out.println(i);
			i++;
		}
		//문제2. 1 ~ 15 까지 출력해보세요. 
		int i = 1; 
		while(i <= 15) {
			System.out.println(i);
			i++;
		}
		//문제3. 0 ~ 100까지 10단위로 출력해보세요. 0 10 20 30 ...
		int i = 0; 
		while(i <= 100) {
			System.out.println(i);
			i+=10;
		}
		//문제4. 1 ~ 20 까지 홀수만 출력해보세요. 
		int i = 1; 
		while(i <= 20) {
			if(i % 2 == 1) {
				System.out.println(i);
			}
			i++;
		}
		//문제5. 1 ~ 10 까지의 합을 출력해보세요. 
		int i = 1; 
		int total = 0;  // 누적된 값을 들고있을 변수를 미리 선언 
		while(i <= 10) {
			total += i; // 누적변수에 i 누적해서 저장 
			i++;
		}
		System.out.println(total);
		
		//문제6. 1 ~ 50 까지의 짝수의 합을 출력해보세요. 
		int i = 1;
		int tot = 0; 
		while(i <= 50) {
			if(i % 2 == 0) { // i 가 짝수라면 
				tot += i; // 누적해라 
			}
			i++;
		}
		*/
		
		/*문제7. 주문 프로그램  
		  	*** 더조은 카페 메뉴 ***
		  	1. 아메리카노 : 2500원
		  	2. 카페라떼 : 3000원
		  	3. 카푸치노 : 3500원
		  	4. 카라멜마끼아또 : 4000원
		  	5. 샌드위치 : 6000원
		  	6. 종료 
		  단계1. 메뉴를 출력하고, 메뉴 번호로 주문을 받는다 
		  		종료를 선택할때까지 주문받을 수 있고, 종료 선택시 주문한 총 금액 출력,종료.
		  단계2. int money = 20000; 내 돈에서 주문금액 차감, 돈이 부족하면 주문 못하게하고, 
		  		총액 출력시 잔액도 출력 
		*/
		Scanner sc = new Scanner(System.in);
		int tot = 0; 	// 주문한 금액을 누적해서 가지고 있을 변수 미리 만들기 
		while(true) {
			System.out.println("*** 더조은 카페 메뉴 ***\n"
					+ "1. 아메리카노 : 2500원\n"
					+ "2. 카페라떼 : 3000원\n"
					+ "3. 카푸치노 : 3500원\n"
					+ "4. 카라멜마끼아또 : 4000원\n"
					+ "5. 샌드위치 : 6000원\n"
					+ "6. 종료");
			System.out.println("주문하실 메뉴 번호를 눌러주세요>>");
			int num = Integer.parseInt(sc.nextLine());
			System.out.println("------------------------------------------------");
			if(num == 1) {
				System.out.println("아메리카노를 주문하셨습니다.");
				tot += 2500; 
			}else if(num == 2) {
				tot += 3000;
			}else if(num == 3) {
				tot += 3500;
			}else if(num == 4) {
				tot += 4000;
			}else if(num == 5) {
				tot += 6000;
			}else if(num == 6) { // 종료 시점 
				System.out.println("주문 총액 : " + tot + "원");
				break; //  while문 빠져나가라 = 강제 종료! 
			}else {
				System.out.println("잘못된 메뉴번호 입력하셨습니다. 다시 눌러주세요.");
			}
		}
		System.out.println("정상종료!!");
		
		//문제8. -1이 입력될때까지 정수를 계속 입력받아, 합과 평균을 구하여 출력해보세요. 
		//문제9. 문자열을 입력받아 출력하되, exit를 입력받으면 종료되는 프로그램. 
		// 		힌트: String문자열 비교는 String의 기능중 .equals()메서드를 이용 
		//		String str = "abc"; 
		//		System.out.println(str.equals("abc")); ==> true 출력됨. 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sc.close();
		
		
		
		
		
		
		
		
		
		
		
	}
}
