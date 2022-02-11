package day05;

class TvTest{
	// 멤버 변수들은 초기화를 안해주면, 기본 값을 자동으로 채워준다
	// int는 0, String은 null, char는 " "(공백으로 나옴)
	// 클래스 기본형 변수
	static int x;
	
	// 인스턴스 기본형 변수
	int y;
	char c;
	
	// 인스턴스 참조형 변수
	String str;

}

public class Test54 {
	public static void main(String[] args) {
		
		System.out.println(TvTest.x);
		TvTest t = new TvTest();
		System.out.println(t.y); // 기본형 자동 초기값 : 0
		System.out.println(t.c); // " "
		System.out.println(t.str); // 참조형 자동 초기값 : null
		
		
		
		// 지역 기본형 변수 : 초기화를 안해주면 출력 X (사용x)
		int a;
//		System.out.println(a);
		String ss;
//		System.out.println(ss);
	}
}
