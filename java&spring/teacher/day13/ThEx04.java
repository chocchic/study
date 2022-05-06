package day13;

// 2. Runnable 인터페이스 구현하는 방식 
class TimeRunnable implements Runnable {
	int n = 0; 
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		while(true) {
			n++; 
			System.out.println(n);
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
				break;
			}
		}// while
	}// run
}// class


public class ThEx04 {
	public static void main(String[] args) {

		Thread th = new Thread(new TimeRunnable()); 
		th.start();
		Thread th2 = new Thread(new TimeRunnable()); 
		th2.start();
		
		
		
		
		
		
		
		
		
	}
}
