package day05;

// #1. 클래스 정의
class Tv{ // 한 파일 내에서 public은 하나만 가능
	//Tv의 상태를 값으로 갖고 있게 변수 생성
	boolean power = false;
	int vol = 0, ch=1;
}

public class Test52 {
	// 변수
	int abc = 123;
	
	// 메서드
	public static void main(String[] args) {
		// Test52 또한 클래스이기 때문에 객체 생성이 가능하다.
		//가능은 하지만 무한 메모리 로딩에 빠질 수 있으니 지양
		Test52 test = new Test52();
		System.out.println(test.abc); // abc의 값 123이 출력된다.
		
		String str = new String(); //String도 클래스형 타입이기 때문에 객체 생성이 가능하다.
				
		// #2. 객체 생성
		Tv t = new Tv();
		System.out.println(t.power);
		System.out.println(t.vol);
		System.out.println(t.ch);
		
		Tv t1 = new Tv();
		Tv t2 = new Tv();
		Tv t3 = new Tv();
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		//클래스명@객체 고유번호(일련번효)
		
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
		t2 = t1; // t2와 연결되어 있던 객체는 쓰레기가 됨 -> garbage collection이 정리
		
		// 불가능(다른 타입)
		//t1 = t2.ch; // t1은 Tv타입이고, t2.ch은 int타입이므로 오류
		//t1 = t2.power; // t1은 Tv타입이고, t2.power은 int타입이므로 오류
		
		// 가능 (같은 타입)
		t1.ch = t2.ch;
		t2.power = t3.power;
		a = t1.ch;
		boolean play = t3.power;
				
	}
}
