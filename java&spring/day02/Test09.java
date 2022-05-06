package day02;
import java.util.Scanner;

public class Test09 {
	public static void main(String[] args) {
		//scanner를 통해서 이름, 혈액형, 나이, 주소를입력받고 출력해보세요
		Scanner sc = new Scanner(System.in);
		
		// 키입력
		System.out.println("키 입력 : "); //화면에 메세지 출력
		String height = sc.nextLine(); // 입력받아 변수에 저장
		System.out.println(height); //화면에 입력받은 결과 출력
		
		double dHeight = Double.parseDouble(height);
		
		System.out.println("이름을 입력하세요 : ");
		String name = sc.nextLine();
		
		System.out.println("혈액형을 입력하세요 : ");
		String blood = sc.nextLine();
		System.out.println("나이를 입력하세요 : ");
		String age = sc.nextLine();
		System.out.println("주소를 입력하세요 : ");
		String address = sc.nextLine();
		System.out.println("이름 : " + name + "\n혈액형 : "+ blood + "\n나이 : " + age + "\n주소 : "+address);
	}
}
