package day10;

public class TestEX03 {
	public static void main(String[] args) {
		int num = 100;
		int result = 0;
/*		try {
		for(int i = 0; i< 10; i++) {
//			ArithmeticException: / by zero : 난수가 0이 나오면 터지는 예외
			result = num/(int)(Math.random()*10);
			System.out.println(result);
					
		}
		}catch(ArithmeticException e) {
			// 예외 발생시 for문 도는것을 멈추고 이 아래로 나머지 코드 실행하고 종료
			System.out.println("0 발생");
		}
*/
		// for문 안에 try-catch 넣을시
		for(int i = 0; i < 10; i++) {
			try {
				result = num/(int)(Math.random()*10);
				System.out.println(result);
			}catch(ArithmeticException e) {
				System.out.println("0 발생");
			}
		}
			
		System.out.println("프로그램 종료");
	}
}
