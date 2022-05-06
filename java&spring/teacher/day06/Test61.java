package day06;
class TestB {
	// 입력x, 리턴x 
	void hello() {
		System.out.println("hello~~~~");
		System.out.println("hello~~~~");
		System.out.println("hello~~~~");
	}
	// 입력X, 리턴O
	int getTen() {
		System.out.println("10을 돌려줄게~~~");
		return 10; 
	}
	// 입력O, 리턴X
	void printName(String name) { // 매개변수도 지역변수
		System.out.println(name + "님 안녕하세요..");
	}
	// 입력O, 리턴O 
	int sum(int a, int b) {
		//int sum = a + b; 
		//return sum;
		return a + b; 
	}
}
public class Test61 {
	public static void main(String[] args) {

		System.out.println("프로그램 시작!! ");
		TestB b = new TestB(); 
		//int result = b.sum(10, 20);
		System.out.println(b.sum(10, 20));
		
		
		
		/*
		b.hello(); // 호출 (메서드야~ 실행해~~) 
		System.out.println("메서드 실행됐니???");
		
		int result = b.getTen();
		System.out.println("result : " + result);
		
		b.printName("피카츄");
		*/
		
		
		
		
		
		
	}
}
