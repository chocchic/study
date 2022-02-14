package day06;

class TestA {
	
	// 인스턴스변수에 자기자신타입으로 객체 생성하면 안된다!!!!!
	// TestA atest = new TestA(); 
	
	int a; // 인스턴스변수 -> 객체생성후 사용가능 
	static int b; 
	Test60 t;
	static String abc; 
	int bbb;
	void test() {
		int def; 
		//static int st; 불가능 
	}
}
public class Test60 {
	public static void main(String[] args) {

		TestA aaa = new TestA(); 
		System.out.println(aaa.a);
		System.out.println(TestA.b);
		
		
	}
}








