package day12;

import java.util.ArrayList;
import java.util.Scanner;

/*
	ArrayList 를 이용하여 강수량의 평균을 유지 관리하는 프로그램을 작성하세요. 
	강수량을 입력하면 벡터에 추가하고 현재 입력된 모든 강수량과 평균을 출력한다. 
	실행 예시 :
		강수량 입력 (-1 입력시 종료) >>  5 (<-사용자 입력) 
		5
		현재 평균 5
		강수량 입력 (-1 입력시 종료) >>	 80
		5 80 
		현재 평균 42
		강수량 입력 (-1 입력시 종료) >>  53
		5 80 53
		현재 평균 46
		강수량 입력 (-1 입력시 종료) >>  22
		5 80 53 22
		현재 평균 40 
		강수량 입력 (-1 입력시 종료) >>  -1
		프로그램 종료 
*/
public class CollEx06 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> rain = new ArrayList<>();
		while(true) {
			System.out.print("강수량 입력 (-1 입력시 종료) >>  ");
			int nrain = Integer.parseInt(sc.nextLine());
			if(nrain == -1) {
				System.out.println("프로그램 종료");
				break;
			}
			rain.add(nrain);
			int total = 0;
			for(int r : rain) {
				System.out.print(r + " ");
				total+=r;
			}
			System.out.println();
			System.out.println("현재 평균 " + total / rain.size());
		}
		
	}
}
