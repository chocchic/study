package day05;

// #1. 클래스 정의 
class Tv {
	// Tv의 상태를 값으로 갖고 있게 변수 생성 
	boolean power = false; 
	int vol = 0, ch = 1; 
}

public class Test52 {
	// 변수 
	int abc = 123; 
	// 메서드 
	public static void main(String[] args) {

		// Test52 또한 클래스이기 때문에 객체 생성이 가능하다!!!
		Test52 test = new Test52(); 
		System.out.println(test.abc);
		
		String str = new String();
		
		
		
		
		
		// #2. 객체 생성 
		Tv t = new Tv(); 
		System.out.println(t.power);
		System.out.println(t.ch);
		System.out.println(t.vol);
		
		Tv t1 = new Tv(); 
		Tv t2 = new Tv(); 
		Tv t3 = new Tv(); 
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		// 클래스명@객체고유번호(일련번호) 
		
		// 각각 다른 자기만의 공간에 저장된다. 
		t1.ch = 10; 
		t2.ch = 20; 
		System.out.println(t1.ch);
		System.out.println(t2.ch);
		
		// 같은 타입끼리 대입 가능.
		int a = 10; 
		int b = 20; 
		a = b; 
		b = a; 
		
		t1 = t2; 
		t2 = t1; 
		
		// 불가능 (다른타입) 
		//t1 = t2.ch;
		//t1 = t2.power; 
		
		// 가능 (같은 타입) 
		t1.ch = t2.ch; 
		t2.power = t3.power; 
		
		a = t1.ch;
		boolean play = t3.power;
		
		
		
		
		
		
	}
}






