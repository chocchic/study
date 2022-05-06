package day14;

class UseLocal {
	void func(int x) {
		int localVar = 50; 
		 
		MyFunctionalInterface2 my = () -> {
			System.out.println("x : " + x);
			System.out.println("localVar : " + localVar);
		};
		
		my.method();
	}
}

public class RamdaEx03 {
	public static void main(String[] args) {

		UseLocal ulocal = new UseLocal(); 
		ulocal.func(20);

		/*
		// Runnable 객체 람다로 (스레드가 할일을 run()메서드에 오버라이딩) 
		Runnable runnable = () -> {
			for(int i = 0; i < 10; i++) {
				System.out.println(i);
			}
		};
		
		Thread thread = new Thread(runnable); 
		thread.start();
		*/
		
		Thread thread = new Thread(() -> {
			for(int i = 0; i < 10; i++) {
				System.out.println(i);
			}
		}); 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
