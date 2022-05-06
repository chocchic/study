package day10;

public class TestEx05 {
	public static void main(String[] args) {

		System.out.println(1);
		try {
			System.out.println(2);
			//System.out.println(0/0); // 일부로 예외 발생 시킴 
			System.out.println(3);
		}catch(Exception e) {
			System.out.println(4);
		}finally {
			System.out.println(5);
		}
		System.out.println(6);
		
		
	}
}
