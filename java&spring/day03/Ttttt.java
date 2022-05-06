package day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ttttt {
public static void main(String[] args) {
	System.out.println("*** 더조은 카페 메뉴 ***\n1. 아메리카노\t: 2500원\n2. 카페라뗴\t: 3000원\n3. 카푸치노\t: 3500원\n4. 카라멜마끼아또\t: 4000원\n5. 샌드위치\t: 6000원\n6. 종료");
	Scanner sc = new Scanner(System.in);
	int money = 20000;
	int sum = 0;
	int i;
	while(true) {
		System.out.print("메뉴 번호를 입력하세요 : ");
		i = Integer.parseInt(sc.nextLine()); 
		Integer[] menu = {2500,3000, 3500,4000,6000};  
		if(5>=i && i>=1) {
			if((money-menu[i-1])<0) {
				System.out.println("돈이 부족합니다.");
				System.out.println("총액 : " + sum + " 잔액 : " + money);
				break;
			}
			money-=menu[i-1];
			sum+=menu[i-1];
		}else if(i==6) {
			System.out.println("주문을 종료합니다.");
			System.out.println("총액 : " + sum + " 잔액 : " + money);
			break;
		}else {
			System.out.println("잘봇된 입력입니다.");
		}
	}
}
}
