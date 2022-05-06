package day10;

public class TestEx09 {
	
	static void method1() throws Exception {
		try {
			throw new Exception();
		}catch(Exception e) {
			System.out.println("method1에서 예외 처리 ");
			throw e;
		}
	}
	public static void main(String[] args) {
		
		try {
			method1(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
