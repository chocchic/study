package day10;

public class TestEx08 {
	
	static void method1() throws Exception {
	
		method2();  // 부르는쪽에서 예외처리 
		
	}
	static void method2() throws Exception {
		// 기능구현을했고 실행하다 예외 발생했다 
		throw new NullPointerException(); 
	}
	
	public static void main(String[] args) {

		// 메서드를 호출해서 사용하는 쪽에서 
		// 상황에 맞게 예외 처리  
		try {
			method1(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
