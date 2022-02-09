package day03;

import java.util.Scanner;

public class Test22 {
	public static void main(String[] args) {
		// 문제 1. 
		int i = 0;
		while(i<11) {
			System.out.println(i);
			i++;
		}
		// 문제 2.
		i=1;
		while(i<16) {
			System.out.println(i);
			i++;
		}
		// 문제 3.
		i = 0;
		while(i<=100) {
			System.out.println(i);
			i+=10;
		}
		// 문제 4.
		i = 1;
		while(i<21) {
			if(i%2==1) {
				System.out.println(i);
			}
			i++;
		}
		// 문제 5.
		i = 1;
		int sum = 0;
		while(i<11) {
			sum+=i++;
		}
		System.out.println(sum);
		// 문제 6.
		i = 1;
		sum = 0;
		while(i<51) {
			if(i%2==0) {
				sum+=i;
			}
			i++;
		}
		System.out.println(sum);
		// 문제 7.
		System.out.println("*** 더조은 카페 메뉴 ***\n1. 아메리카노\t: 2500원\n2. 카페라뗴\t: 3000원\n3. 카푸치노\t: 3500원\n4. 카라멜마끼아또\t: 4000원\n5. 샌드위치\t: 6000원\n6. 종료");
		Scanner sc = new Scanner(System.in);
		int money = 20000;
		sum = 0;
		while(true) {
			System.out.print("메뉴 번호를 입력하세요 : ");
			i = Integer.parseInt(sc.nextLine()); 
			if(i==1) {
				if((money-2500)<0) {
					System.out.println("돈이 부족합니다.");
					System.out.println("총액 : " + sum + " 잔액 : " + money);
					break;
				}
				money -=2500;
				sum+=2500;
			}else if(i==2) {
				if((money-3000)<0) {
					System.out.println("돈이 부족합니다.");
					System.out.println("총액 : " + sum + " 잔액 : " + money);
					break;
				}
				money -=3000;
				sum+=3000;
			}else if(i==3) {
				if((money-3500)<0) {
					System.out.println("돈이 부족합니다.");
					System.out.println("총액 : " + sum + " 잔액 : " + money);
					break;
				}
				money -=3500;
				sum+=3500;
			}else if(i==4) {
				if((money-4000)<0) {
					System.out.println("돈이 부족합니다.");
					System.out.println("총액 : " + sum + " 잔액 : " + money);
					break;
				}
				money-=4000;
				sum+=4000;
			}else if(i==5) {
				if((money-6000)<0) {
					System.out.println("돈이 부족합니다.");
					System.out.println("총액 : " + sum + " 잔액 : " + money);
					break;
				}
				money -=6000;
				sum+=6000;
			}else if(i==6) {
				System.out.println("주문을 종료합니다.");
				System.out.println("총액 : " + sum + " 잔액 : " + money);
				break;
			}else {
				System.out.println("잘봇된 입력입니다.");
			}
		}
		// 문제 8.
		sum = 0;
		while(true) {
			System.out.print("정수를 입력하세요 -1입력시 종료 : ");
			i = Integer.parseInt(sc.nextLine()); 
			if(i==-1)break;
			sum+=i;
		}
		System.out.println(sum);
		// 문제 9.
		String str;
		while(true) {
			System.out.print("문자열을 입력하세요 exit 입력시 종료(대소문자 구분 O): ");
			str = sc.nextLine();
			if(str.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			System.out.println(str);
		}
	}
}
