package day02;
/*
#1. import 문 : Scanner 클래스스가 있는 자바 제공 패키지를 여기서 사용하겠다~~
			import java.util.Scanner;
#2. Scanner 객체 생성 
	Scanner sc = new Scanner(System.in);
#3. 입력받기 
	String 변수명 = sc.nextLine();  문자열 String 타입으로 리턴해줌 
	nextLine()은 입력받고싶은만큼 계속 사용 가능. 
#4. Scanner 객체 닫기 
	sc.close(); 
*/
import java.util.Scanner;	// #1. import문 작성 

public class Test08 {
	public static void main(String[] args) {

		// #2. Scanner 객체 생성 
		Scanner sc = new Scanner(System.in);
		
		// #3. 입력받기 
		System.out.println("이름을 입력하세요 :");
		String abc = sc.nextLine(); 
		System.out.println("이름은 " + abc + " 입니다.");
		
		System.out.println("나이를 입력하세요 : ");
		String def = sc.nextLine(); 
		// 형변환 : 클래스인 String과 기본형 int는 서로 호환이 잘 안된다. 
		//			그래서 String -> int로 형변환할때는 
		//			특별한 클래스를 사용해야된다. 
		int defInt = Integer.parseInt(def); 
		
		// 한줄로 줄이기 
		//int age = Integer.parseInt(sc.nextLine());
		
		System.out.println("내년 나이는 " + (defInt+1) + "살 입니다.");
		
		// 원하는만큼 입력받기 
		
		
		
		// #4. 객체 닫기 
		sc.close();
		
	}
}
