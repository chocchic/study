package day05;
// 같은 패키지내에선 같은 클래스명 사용이 불가능하다.
//class Tv{} // Test52에 이미 Tv라는 클래스를 만들었기 때문에 불가능
class TvEx{
	//클래스 변수 : 프로그램 종료되어야 없어짐
	static boolean power = false;
	
	//인스턴스 변수 : 클래스 내에 선언되었음. 
	int vol = 0, ch = 1;
	
	//static의 유무로 클래스인지 인스턴스인지 구분.
	
	void volUp() {
		//지역변수 : volUp이라는 메서드내에서 선언되어서 
		int vol = 10;
	}
}
public class Test53 {
	//클래스 메서드
	public static void main(String[] args) {
		// 지역변수(기본형) : main메서드의 중괄호까지만 살아있다.
		int abc = 10; //main이라는 메서드 내에서 만들어짐
		for(int i = 0; i<10;i++) {
			int aaa = 0;
			System.out.println(i);
		}
		//System.out.println(aaa); //for문 내에서만 쓸 수 있는것을 확인할 수 있다.
		
		//클래스 변수 : 객체 생성안하고 바로 사용 가능
		System.out.println(TvEx.power);
		
		//인스턴스 변수 : 객체 생성 후 사용 가능
		TvEx tv = new TvEx();
		System.out.println(tv.vol);
		System.out.println(tv.ch);
		System.out.println(tv.power); 
		// 가능은 하지만 이 코드만 봐서는 클래스 변수인지 인스턴스 변수인지 구분하기 어려우므로 
		// 클래스명.power로 작성(TvEx.power)

	}
}
