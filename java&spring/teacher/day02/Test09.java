package day02;
import java.util.Scanner;

public class Test09 {
	public static void main(String[] args) {

		// Scanner 를 통해서 이름, 혈액형, 나이, 주소를 입력받고 
		//	출력해보세요.
		Scanner sc = new Scanner(System.in);
		
		// 키 입력 
		System.out.println("키 입력 :"); // 화면에 메세지 출력 
		String height = sc.nextLine();	// 입력받아 변수에 저장 
		System.out.println(height);		// 화면에 입력받은 결과 출력 
		
		double dHeight = Double.parseDouble(height);
		
		// String -> int : Integer.parseInt(String값);
		// String -> double : Double.parseDouble(String값);
		
		/*
		System.out.println("이름 입력 : ");
		String name = sc.nextLine(); 
		System.out.println("이름 : " + name);
		
		System.out.println("혈액형 입력 : ");
		String blood = sc.nextLine(); 
		System.out.println("혈액형 : " + blood);
		
		System.out.println("나이 입력 : ");
		//String age = sc.nextLine(); 
		int age = Integer.parseInt(sc.nextLine());
		System.out.println("나이 : " + age);
		
		System.out.println("주소 입력 : ");
		String addr = sc.nextLine(); 
		System.out.println("주소 : " + addr);
		*/
		
		
		
	
		
		
		sc.close();
		
	}
}
