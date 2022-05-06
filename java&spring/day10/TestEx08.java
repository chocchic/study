package day10;

public class TestEx08 {
//	static void method1() {
//		try {
//			method2(); // throws로 받았기 때문에 여기서도 처리해줘야함.
//		}catch(Exception e) {
//			
//		}
//	}
//	static void method2() throws Exception {
//		// 기능 구현을 했고 실행하다 예외 발생
//		throw new Exception(); // throws Exception만 쓰면 예외 처리 부족으로 throws~ 이걸 붙이거나 try-catch로 묶어야함.
//		// throws -> 날 가져다 쓰는 애가 예외처리를 해라
//	}
//	public static void main(String[] args) {
//		method1();		
//	}
	
	static void method1() throws Exception{ // 다시 던지기를 선택
			method2(); // throws로 받았기 때문에 여기서도 처리해줘야함.			
	}
	static void method2() throws Exception {
		// 기능 구현을 했고 실행하다 예외 발생
		throw new Exception(); // throws Exception만 쓰면 예외 처리 부족으로 throws~ 이걸 붙이거나 try-catch로 묶어야함.
		// throws -> 날 가져다 쓰는 애가 예외처리를 해라
	}
	public static void main(String[] args) {
		
		//메서드를 호출해서 사용하는 쪽에서 상황에 맞게 예외처리
		try {
			method1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
