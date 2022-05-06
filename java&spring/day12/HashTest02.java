package day12;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashTest02 {
	public static void main(String[] args) {
		// 영한 사전 만들기
		// HashMap을 이용하여 10개의 단어를 영어, 한글의 쌍으로 저장하고 입력받은 영어의 한글 뜻을 출력해주는 프로그램을 만들어 보세요.
		// 10개 단어는 미리 저장해놓고 exit을 입력할 때까지 계속 검색할 수 있게 만드세요
		// 찾는 단어가 없으면 "찾는 단어가 없습니다.." 이런 메세지 출력
		Scanner sc = new Scanner(System.in);
		HashMap<String,String> dic = new HashMap<>();
		dic.put("apple","사과");
		dic.put("strawberry","딸기");
		dic.put("watermelon","수박");
		dic.put("peach","복숭아");
		dic.put("grape","포도");
		dic.put("banana", "바나나");
		dic.put("pear","배");
		dic.put("cherry","체리");
		dic.put("pineapple","파인애플");
		dic.put("blueberry","블루베리");
//		Map<String,String> dic2 = Map.of("apple","사과","strawberry","딸기","watermelon","수박","peach","복숭아",
//				"grape","포도","banana", "바나나","pear","배","cherry","체리","pineapple","파인애플","blueberry","블루베리");
		while(true) {
			System.out.print("단어를 입력하세요 >>");
			String word = sc.nextLine();
			if(word.equals("exit")) {
				System.out.println("사전 종료");
				break;
			}else {
				if(dic.containsKey(word)) {
					System.out.println(word + "의 한국어 뜻 : " + dic.get(word));
				}else {
					System.out.println("찾는 단어가 없습니다.");
				}
				// String kor = dic.get(word);
				//if(kor.equals(null)) ... 없으면 null 리턴이라는거 이용해도 됨
			}
			
		}
		
	}
}
