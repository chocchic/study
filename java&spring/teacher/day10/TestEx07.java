package day10;

public class TestEx07 {
	public static void main(String[] args) {

		try {
			//Exception e = new Exception();
			//NullPointerException e = new NullPointerException(); 
			RuntimeException e = new RuntimeException();
			throw e; 
			
		}catch(NullPointerException e) {
			System.out.println("Null Pointer Exception 처리");
		}catch(RuntimeException e) {
			System.out.println("Runtime Exception 처리");
		}catch(Exception e) {
			System.out.println("모든예외 처리");
		}
		
		
		
		
	}
}
