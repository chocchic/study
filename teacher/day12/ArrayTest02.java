package day12;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayTest02 {
	public static void main(String[] args) {

		// 영어 이름을 4개 입력받아 ArrayList에 저장하고
		// 모든데이터에 직접 접근해 출력한 후 제일 긴 이름을 출력하세요. 
		ArrayList<String> arr = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 4; i++) {
			System.out.print("이름입력>>");
			String s = sc.nextLine(); 
			arr.add(s);  // arraylist에 저장 
		}
		
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i)); 
		}
		
		int longestIdx = 0; 
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(longestIdx).length() < arr.get(i).length()) {
				longestIdx = i; 
			}
		}
		System.out.println("가장 긴 이름 : " + arr.get(longestIdx));
		
		
		
		sc.close();
		
	}
}
