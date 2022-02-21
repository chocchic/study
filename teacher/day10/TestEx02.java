package day10;

public class TestEx02 {
	public static void main(String[] args) {

		// ArrayIndexOutOfBoundsException
		
		try {
			System.out.println(args[0]);
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("인수를 입력안하고 실행하셨습니다.");
			System.out.println(e);
		}
		
		
		
		
		
	}
}
