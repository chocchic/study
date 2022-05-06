package day13;

class TimerThread extends Thread {
	int n = 0; 
	@Override
	public void run() {
		System.out.println("run 실행");
		while(true) {
			n++; 
			System.out.println(n);
			try {
				sleep(1000); 
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}//while
	}//run
}//class 

public class ThEx02 {
	public static void main(String[] args) { // main스레드 기본적으로 1개 존재 

		System.out.println("main메서드 실행 ");
		
		TimerThread th = new TimerThread(); 
		th.start(); // 스레드 시작!!
		
		System.out.println("main 메서드 종료");

		
		
		
	}
}
