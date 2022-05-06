package day12;

import java.util.HashMap;
import java.util.Scanner;

public class HashTest02 {
	public static void main(String[] args) {
		// 영한 사전만들기
		// HashMap을 이용하여 10개의 단어를 영어, 한글의 쌍으로 저장하고 
		// 입력받은 영어의 한글뜻을 출력해주는 프로그램을 만들어 보세요. 
		// 10개 단어는 미리 저장해놓고, 
		// exit를 입력할때까지 계속 영단어를 입력받아 검색할 수 있게 만드세요. 
		// 찾는 단어가 없으면 "찾는 단어가 없습니다.." 이런 메세지 출력. 
		
		// 객체 생성 
		HashMap<String, String> h = new HashMap<String, String>();
		Scanner sc = new Scanner(System.in);
		// 요소 저장 
		h.put("apple", "사과");
		h.put("strawberry", "딸기");
		h.put("watermelon", "수박");
		h.put("peach", "복숭아");
		h.put("pear", "배");
		h.put("banana", "바나나");
		h.put("mandarine", "귤");
		
		while(true) {
			System.out.print("찾고싶은 단어>>");
			String eng = sc.nextLine();
			if(eng.equals("exit")) {
				System.out.println("프로그램 종료.");
				break;
			}
			
			String kor = h.get(eng);
			if(kor == null) {
				System.out.println(eng + "는 없는 단어 입니다.");
			}else {
				System.out.println(kor);
			}

		}
		
		
		
		
		
		
		
		
		
		
		sc.close();
		
	}
}
