package day13;

class TimerThread extends Thread{
	
	int n = 0;
	
	@Override
	public void run() {
		while(true) {
			n++;
			System.out.println(n);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}// while
	}// run
}// class

public class ThEx01 {
	public static void main(String[] args) { // main 스레드 기본적으로 1개 존재.
		System.out.println("main 메서드 실행");
		TimerThread th = new TimerThread(); // timer를 찍는 스레드도 존재. -> main이 종료해도 프로그램 종료 안되면 계속 실행
		th.start(); // 스레드 시작
		System.out.println("main 메서드 종료");
	
	}
}
