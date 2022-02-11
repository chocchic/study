package day05;

class Tv2{
	//인스턴스 기본형 변수
	int a;
	boolean b;
	float f;
	double d;
	
	//인스턴스 참조형 변수
	String str;
	Tv t; //Test52파일에서 생성했음
	Test55 test;
	TvEx tt;
	
	//클래스 기본형 변수
	static int ab;
	static TvEx ttt;
	
	
	//static int x =y; //불가능. x가 생성 되는 시점에 y가 존재하지 않기때문 
	//int y = 20;
	
	int y = x;
	static int x = 100; // static이 있기 떄문에 이미 생성됨.
	
}

public class Test55 {
	int abc;
	String str;
	static int def;
	static Tv t;
	static int a = 100;
	int i = 20;
	public static void main(String[] args) {
		System.out.println(Tv2.x);
		TvTest t = new TvTest();
		System.out.println(t.y);
		
		System.out.println(Test55.a);
		Test55 tt = new Test55();
		System.out.println(tt.i);
		
		System.out.println(new Test55().i);// 단발성 객체 생성 1회성으로 사용할 때 가능.
	}
}
