package day10;

public class TestEX03 {
	public static void main(String[] args) {

		int num = 100; 
		int result = 0; 
		for(int i = 0; i < 10; i++) {
			try {
				// ArithmeticException : 랜덤이 0이 나오면 터지는 예외 
				result = num / (int)(Math.random() * 10); 
				System.out.println(result);
				
			}catch(ArithmeticException e) {
				System.out.println("0 발생");
			}
		}
		System.out.println("프로그램 종료");
		
	}
}
