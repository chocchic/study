package day02;

public class Test19 {
	public static void main(String[] args) {

		int a = 1; 
		switch(a) {
		case 10:
			System.out.println("a는 1이다");
			break;
		case 233:
			System.out.println("a는 2다.");
			break;
		case 1:
			System.out.println("a는 10이다.");
			//break;
		}
		
		char ch = 'B';
		switch(ch){
			case 'A':
				System.out.println("A");
				break;
			case 'B':
				System.out.println("BBB");
				break;
			case 'C':
				System.out.println("cccc");
		}
		
		
		
		
		// 숫자 2개 입력받고, 문자도 하나 입력받아 사칙연산 결과 
		String aaa = "+=";
		char aaaCH = aaa.charAt(1);
		System.out.println(aaaCH);
		
		String str = "hello"; 
		switch(str) {
		case "hello":
			System.out.println("helloooooooo");
			break;
		case "ttttt":
			System.out.println("tttttt");
		}
		
		
		
		
		
		
		
		
		
		
	}
}
