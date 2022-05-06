package day02;
/*
		1. import 문 : Scanner 클래스가 있는 자바 제공 패키지를 여기서 사용하겠다
			ex ) import java.util.Scanner;
			+ 클래스밖 패키지 밑에 선언
		2. Scanner 객체 생성
			Scanner sc = new Scanner(System.in);
		3. 입력받기
			sc.nextLine(); // 문자열 String 타입으로 return. 입력 받고 싶은 만큼 계속 사용 가능.
			ex) String 변수명 = sc.nextLine();
		4. Scanner 객체 닫기
			sc.close();
*/
import java.util.Scanner; // 1.

public class Test08 {
	public static void main(String[] args) {
		//2. Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		
		//3. 입력받기
		System.out.println("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä : ");
		String str = sc.nextLine();
		System.out.println("ÀÌ¸§Àº " + str+ " ÀÔ´Ï´Ù.");
	
		System.out.println("³ªÀÌ¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
		String def = sc.nextLine();
		
		//형변환
		//String은 참조형이므로 변환X(String는 클래스)
		//int defInt = (int) def;
		// 그래서 위에서 에러 발생
		// String -> int : Integer.parseInt(String 값); 이용해서 값 변환
		int defInt = Integer.parseInt(def);
		//한줄로입력받기
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("내년 나이는" + (defInt+1) + "입니다.");
				
		//4. scanner 객체 받기
		sc.close();
	}

}
