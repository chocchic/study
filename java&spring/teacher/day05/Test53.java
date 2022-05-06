package day05;

class TvEx {
	// 클래스 변수 
	static boolean power = false; 
	// 인스턴스 변수 
	int vol = 0, ch = 1; 
}
public class Test53 {
	// 클래스 메서드 
	public static void main(String[] args) {


		
		// 클래스 변수 : 객체 생성안하고 바로 사용가능 
		System.out.println(TvEx.power);
		
		// 인스턴스 변수 : 객체 생성후 사용 가능 
		TvEx tv = new TvEx(); 
		System.out.println(tv.vol);
		System.out.println(tv.ch);
		System.out.println(tv.power); // 이 코드만 봐서는 클래스변수인지
		//	알아보기 힘드니 클래스명.power 로 작성하도록 하자. 
		
		// 지역변수 
		//int abc = 10; 
		
		// 지역변수 
		//System.out.println(abc);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
