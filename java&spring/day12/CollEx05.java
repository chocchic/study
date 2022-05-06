package day12;

import java.util.HashMap;
import java.util.Scanner;

/*
	"그만"이 입력될때까지 나라이름과 인구를 입력받아 저장하고,
	다시 나라이름을 입력받아 인구를 출력하는 프로그램을 작성하세요. 
	HashMap<String, Integer> nations = new HashMap<String, Integer>(); 여기에 저장. 
	실행예시:
		나라 이름과 인구를 입력하세요 (예 : Korea 5000). 
		나라이름, 인구 >>  Korea 5000  (<- 사용자 입력) 
		나라이름, 인구 >>  USA 100000
		나라이름, 인구 >>  Swiss 2000
		나라이름, 인구 >>  France 3000
		나라이름, 인구 >>  그만 
		인구 검색 >>  France
		France의 인구는 3000
		인구 검색 >> 스위스 
		스위스 나라는 없습니다. 
		인구 검색 >> 그만 
		프로그램 종료!!!
*/
public class CollEx05 {
	public static void main(String[] args) {
		HashMap<String, Integer> nations = new HashMap<String, Integer>();
		Scanner sc = new Scanner(System.in);
		System.out.println("나라 이름과 인구를 입력하세요");
		boolean stop = true;
		while(stop) {
			System.out.print("나라이름, 인구 >>  ");
			String input = sc.nextLine();
			if(input.equals("그만")) {
				while(true) {
					System.out.print("인구 검색 >>  ");
					String country = sc.nextLine();
					if(country.equals("그만")){
						System.out.println("프로그램 종료!!!");
						stop = false;
						break;
					}else if(nations.containsKey(country)) {
						System.out.println(country + "의 인구는 " + nations.get(country));
					}else {
						System.out.println(country + " 나라는 없습니다.");
					}
				}				
			}else {
				String[] inputSplit = input.split(" ");
				nations.put(inputSplit[0], Integer.parseInt(inputSplit[1]));
			}
		}
	}
}
