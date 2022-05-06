package day12;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayTest02 {
	public static void main(String[] args) {
		// 문제 : 영어이름 4개를 입력받아 ArrayList에 저장하고 출력. 가장 긴 이름 출력
		
		ArrayList<String> strArr = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i<4;i++) {
			System.out.print("입력 >>  ");
			strArr.add(sc.nextLine());
		}
//		String longest = "";
//		for(String s : strArr) {
//			System.out.println(s);
//			if(longest.length() < s.length()) longest = s;
//		}
//		System.out.println("가장 긴 String : " +  longest);
		int longidx = 0;
		for(int i = 0; i < 4 ; i++) {
			if(strArr.get(i).length() > strArr.get(longidx).length()) longidx = i;
		}
		System.out.println("가장 긴 String : " + strArr.get(longidx));
	}

}
