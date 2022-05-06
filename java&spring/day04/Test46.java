package day04;

import java.util.Scanner;

public class Test46 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		//문제1. 아래와 같은 2차원 배열 arr을 출력하는 프로그램을 작성하세요. 
		/*
			1
			1	2	3
			1
			1	2	3	4
			1	2
		*/
//		int[][] arr = {{1},{1,2,3},{1},{1,2,3,4},{1,2}};
//		for(int i = 0; i<arr.length;i++) {
//			for(int j = 0; j<arr[i].length;j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		//문제2. Scanner로 소문자 알파벳 하나 입력받고, 아래와 같이 출력하는 프로그램을 작성하세요. 
		/*
			소문자 알파벳하나 입력 >> 	e (사용자가 입력) 
			abcde
			abcd
			abc
			ab
			a
		*/
//		System.out.print("알파벳하나를 입력하세요 >> ");
//		String alp = sc.nextLine();
//		char a = alp.charAt(0);
//		for(int i = 0;i<((int)a)-96; i++) {
//			for(int j = 0; j<((int)a-96-i);j++) {
//				System.out.print(((char)(j+97)));
//			}
//			System.out.println();
//		}
		//문제3. 양의 정수 10개를 입력받아 배열에 저장하고, 3의 배수만 출력하는 프로그램을 작성하세요.
//		int[] num = new int[10];
//		for(int i = 0; i< 10;i++) {
//			System.out.print((i+1) + "번째 정수를 입력하세요 >> ");
//			num[i] = Integer.parseInt(sc.nextLine());
//		}
//		System.out.println("배열에서 3의 배수인 수 : ");
//		for(int i = 0; i< 10;i++) {
//			if(num[i]%3==0) {
//				System.out.print(num[i] +" ");
//			}
//		}
		
		//문제4. 4x4의 2차원 배열(총16개방)을 만들고, 1 ~ 10까지 범위의 정수를 10개만 
		//		랜덤하게 생성하여 임의의 위치에 삽입하세요. 
		//		동일한 정수가 있어도 상관 없으며, 나머지 6개의 숫자는 모두 0으로 만들고 
		//		4 x 4 형태로 출력해보세요.
		/*
				예) 
				3	0	7	2
				1	7	0	4
				0	6	4	5
				0	8	0	0
		*/
		
		int count = 0;
		int[][] arr4 = new int[4][4];
		int r = 0,c=0;
		boolean create = false;
		while(count<10){
			while(!create) {
				r = (int)(Math.random()*4);
				c = (int)(Math.random()*4);
				if((arr4[r][c] != 0)) {
					create = false;						
				}else {
					arr4[r][c] = (int)(Math.random()*10+1);
					create = true;
				}
			}
			count++;
			create=false;
		}
		for(int i = 0; i< 4;i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(arr4[i][j] + " ");
			}
			System.out.println();
		}
	}
}
