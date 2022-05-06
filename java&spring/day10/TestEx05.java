package day10;

public class TestEx05 {
	public static void main(String[] args) {
		System.out.println(1);
		try {
			System.out.println(2);
			System.out.println(0/0);
			System.out.println(3); // 0/0을 실행하면 실행안되고 바로 catch문으로 넘어감
		}catch(Exception e) {
			System.out.println(4); // 0/0을 안실행하면 실행 안되고 바로 finally문으로 넘어감
		}finally {
			System.out.println(5);
		}
		System.out.println(6);
	}
}
