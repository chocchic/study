package day06;
class TestB{
	// 입력 X,리턴 X
	void hello() {
		System.out.println("hello");
		System.out.println("hello");
		System.out.println("hello");
	}
	//입력 X, 리턴 O
	int getTen() {
		System.out.println("10을 돌려줄게");
		return 10;
	}
	// 입력 O, 리턴 X
	void printName(String name) { // 매개변수도 지역변수
		System.out.println(name + "님 안녕하세요..");
	}
	// 입력 O, 리턴 O
	int sum(int a, int b) {
		int sum = a+b;
		return sum;
		//return a+b;
	}
}


public class Test61 {
	public static void main(String[] args) {
		TestB b = new TestB();
		b.hello(); // 호출 (메서드야~ 실행해~)
		System.out.println("메서드야 실행됐니?");
		int result = b.getTen();
		System.out.println("result : " + result);
		b.printName("피카츄");
		System.out.println(b.sum(10, 20));
		
	}
}
