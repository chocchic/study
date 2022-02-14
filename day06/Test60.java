package day06;
class TestA{
	//인스턴스 변수에 자기 자신 타입으로 객체 생성하면안된다
	//TestA atest = new TestA();
	int a ;
	static int b;
	Test60 t;
	static String abc;
	void test() {
		int def;
		//static int st; // 메서드 내에선 클래스 변수를 만들 수 없다.
		int bbb;
	}
}
public class Test60 {
	public static void main(String[] args) {
		TestA aaa = new TestA();
		System.out.println(aaa.a);
	}
	
}
