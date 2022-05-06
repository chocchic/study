package day07;

public class Test71 {
	// 클래스 변수 
	static int x = 10; 
	// 클래스 블럭 : 프로그램 실행시 자동으로 한번 실행됨.(재호출 절대 불가능) 
	static { 
		System.out.println("클래스 초기화 블럭");
		x = 200; 
		//y = 300; 
		//int xxx = 100; 
	}
	// 인스턴스 변수 
	int y = 20; 
	// 인스턴스 블럭 : 객체 생성시 자동으로 한번 실행됨.(재호출 절대 불가능) 
	{
		System.out.println("인스턴스 초기화 블럭");
		x = 300; 
		y = 500; 
	}
	public static void main(String[] args) {

		Test71 test = new Test71(); 
		Test71 test1 = new Test71(); 
		
		

	}
}








