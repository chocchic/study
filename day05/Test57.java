package day05;

class Tv5{
	boolean power = false;
	int ch = 1, vol = 0;
}

public class Test57 {
//	int x;
//	Test57 test; // 인스턴스 변수 -> 객체 생성해야 사용가능.
	
	Tv5 tv;
	Test57 test;
	
	public static void main(String[] args) {
//		Test57 a = new Test57();
//		System.out.println(a.test);
//		a.test = new Test57();
				
		Test57 a = new Test57();
		System.out.println(a.test); //null
		if(a.test != null) {
			System.out.println(a.test.tv);
		}else {
			a.test = new Test57();
			System.out.println(a.test.tv);
		}
		
		
	}
}
