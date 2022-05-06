package day05;

class TvTest{
	// 멤버 변수들은 초기화를 안해주면, 기본값을 자동으로 체워준다. 
	// 클래스 기본형 변수 
	static int x; 
	// 인스턴스 기본형 변수 
	int y; 
	// 인스턴스 참조형 변수 
	String str; 
	char c;   
	Tv t; 
	
}

public class Test54 {
	public static void main(String[] args) {

		System.out.println(TvTest.x);
		TvTest t = new TvTest(); 
		System.out.println(t.y);	// 기본형 자동 초기값 0 
		System.out.println(t.str); // 참조형 자동 초기값 null
		System.out.println(t.c);
		
		// 지역 기본형 변수 : 초기화를 안해주면 출력X (사용X) 
		int a; 
		//System.out.println(a);
		// 지역 참조형 변수 
		String ss; 
		//System.out.println(ss);
		
		
	}
}
