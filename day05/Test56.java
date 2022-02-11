package day05;
/*
	클래스 기본형 변수 : static키워드. 클래스명.변수명 
	클래스 참조형 변수

	인스턴스 기본형 변수 : new 객체 생성해서 참조변수명.변수명
	인스턴스 참조형 변수

	지역 기본형 변수 : 메서드 내에 생성. 메서드가 종료되면 같이 없어짐
	지역 참조형 변수
*/
class Tv3{
	boolean power = false;
	int ch = 1, vol = 0;
	
}
public class Test56 {
	// 클래스 영역
	static int a;		// Test56.a로 값 확인 가능
	static Tv3 tv;//=new Tv3(); //용도에 따라 다르지만 객체 생성을 하고 만드는 것이 좋다. // Test56.tv
	static Test56 test;// = new Test56(); // Test56.test
	
	int x = 213;
	Tv3 tv3;
	// 메모리 무한 로딩에 빠짐 : 자신의 클래스로 인스턴스 객체 생성(new)할 때 발생.
	//Test56 test56 = new Test56(); // stack overflow 오류. 여기서 객체 생성까지 X
	Test56 z = null;
	
	public static void main(String[] args) {
//		System.out.println(new Test56().test56); 
		Test56 testtt = new Test56(); // 인스턴스 변수 z를 사용하기 위해 객체 생성
		testtt.z = new Test56();	//z가 객체 생성이 되지 않아 안에 들은 x를 꺼낼 수 없다.
		System.out.println(testtt.z.x);
	}
}
