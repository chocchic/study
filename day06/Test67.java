package day06;

public class Test67 {
	// 가능
	int getSum(int a, int b) {
		return a+b;
	}
	int getSum(int a, int b, int c) {
		return a+b+c;
	}
	// 가능 (타입차이)
	
	int getMul(int a, int b) {
		return a*b;
	}
	double getMul(int a, double b) {
		return a*b;
	}
	/////////////////////////////////////////////
	// 불가능
	/*
	 * int getSub(int a, int b) { return a - b; } 
	 * 
	 * double getSub(int a, int b) { return a - b; }
	 */
	
	String getDiv(int a, String b) {
		System.out.println("1번");
		return a+b;
	}
	String getDiv(String b, int a) {
		System.out.println("2번");
		return a+b;
	}
	
	public static void main(String[] args) {
		Math.abs(0);
		
		Test67 test = new Test67();
		System.out.println(test.getSum(10,20));
		System.out.println(test.getDiv("pikachu", 100));
		
		//메서드 오버로딩은 자바 컴파일러에 의해 판단되며,
		// 컴파일러가 이름이 같은 메ㅓ드들을 구분할 수 있으면 오버로딩 성공
		// 컴파일러가 메서드 이름과 매개변수의 개수/타입 일치하는 메서드를 찾아냄.
	}
}
