package day10;

public class TestEx07 {
	public static void main(String[] args) {
		try {
//			Exception e = new Exception();
//			NullPointerException e = new NullPointerException();
			RuntimeException e = new RuntimeException();
			throw e;
		}catch(NullPointerException e) {
			System.out.println("Null Pointer Exception 예외 처리");
		}catch(RuntimeException e) { //NullPointerException의 부모
			System.out.println("Runtime Exception 예외 처리");
		}catch(Exception e) { //RuntimeException의 부모 
			System.out.println("모든 예외 처리");
		}
	}
}
