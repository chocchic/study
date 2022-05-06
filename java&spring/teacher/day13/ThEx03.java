package day13;

class ThreadEx extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 1000; i++) {
			System.out.println("run");
		}
	}
}
public class ThEx03 {
	public static void main(String[] args) {

		ThreadEx th = new ThreadEx(); 
		ThreadEx th2 = new ThreadEx(); 
		th.start();
		th2.start();
		//th.run(); // 단순 메서드 호출, 스레드 등록 안되서 main 스레드로만 실행 
		
		for(int i = 0; i < 1000; i++) {
			System.out.println("main");
		}
		
		
		
	}
}
