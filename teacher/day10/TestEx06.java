package day10;

public class TestEx06 {
	public static void main(String[] args) {
		/*
		try {
			// 예외를 강제로 발생시킴. 
			throw new RuntimeException(); 
			//System.out.println("hello");
		}catch(Exception e) {
			System.out.println("예외가 발생했네요~~~");
		}*/
		
		try {
			System.out.println("hello");
			Exception e = new Exception("고의로 예외를 발생시킨거야!"); 
			throw e; 
		}catch(Exception e) {
			//System.out.println(e.getMessage());;
			//System.out.println(e);
			e.printStackTrace(); 
			// 예외 발생시, 어디서 에러가 발생했는지 경로 추적할 수 있는 메세지 출력 
			// try-catch로 예외처리를 안하면 콘솔에 뜨는 메세지들(try-catch시 안나옴)을
			// 이 메서드를 통해 다시 출력할수있다. 
		}
		
		
		
		
		
		
		
	}
}
