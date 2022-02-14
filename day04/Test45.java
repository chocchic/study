package day04;

import java.util.Random;
import java.util.Scanner;

public class Test45 {
	public static void main(String[] args) {
		/*	
		문제1. Up & Down 게임 
			0 ~ 99 사이의 임의의 수를 받아 숨기고, 그 수를 맞추는 게임. 
			임의의 수보다 낮게 입력하면 "Up" 출력, 
			높게 입력하면 "Down" 출력하면서 범위를 좁혀가며 수를 맞춘다. 
			게임을 반복하기 위해 y/n으로 묻고, n인 경우 프로그램 종료 
			
		콘솔예) 
		숫자가 정해졌습니다. 맞춰보세요! 
		0~99
		1 >>	55 (사용자가 입력) 
		"Up"
		55~99
		2 >>	70
		"Up"
		70~99
		3 >>	85
		"Down"
		70~85
		4 >>	80
		"Down"
		70~80
		5 >>	75
		"Up"
		75~80
		6 >>	77
		맞았습니다!! 
		다시하시겠습니까?(y/n) >>	y 
		숫자가 정해졌습니다. 맞춰보세요!
		0~99
		1 >>	30
		"Up"
		30~99
		2 >>	45
		맞았습니다!!
		다시하시겠습니까?(y/n) >> 	n
		게임 종료!	
	*/
		Boolean exit = false;
		Boolean correct;
		int count;
		int max=99,min=0;
		Scanner sc = new Scanner(System.in);
		while(!exit) {
			int sol = (int)(Math.random()*100);
			count = 1;
			max=99;
			min=0;
			correct=false;
			System.out.println("숫자(0~99)가 정해졌습니다. 맞춰보세요!");
			while(!correct) {
				System.out.println(count + " >> ");
				int answer = Integer.parseInt(sc.nextLine());
				if(answer > sol) {
					if(max>answer) max=answer;
					System.out.println("\"Down\" : "+min+" ~ "+ max);
					count++;
				}else if(answer < sol) {
					if(min<answer) min = answer;
					System.out.println("\"Up\" : "+min+ " ~ " + max);
					count++;
				}else {
					System.out.println("맞았습니다. 다시 시작하시겠습니까?(y/n)  >>");
					String e = sc.nextLine();
					if(e.equals("n")) {
						System.out.println("게임 종료!");
						exit = true;
					}
					correct = true;
				}
			}
		}
	}
}
