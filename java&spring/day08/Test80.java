package day08;
// final
// #1.
/*
final class Super{
	
}
class Sub extend Super{
	
}
*/ // Super에 final 붙이면 불가능
// #2.
class Super{
	final void add() {System.out.println("hahahaha");}
}
class Sub extends Super {
	// 오버라이딩 불가. final은 진짜 최종임
//	void add() {System.out.println("hohhohoho");}
}

class Sup {
	/* 이미 명시적으로 초기화가 된 상수는 값 변경이 안된다.
	final int x = 10;
	Sup(int x){
		this.x = x;
	}
	*/
	
	//예를 들어, 객체 생성시 외부에서 던져주는 값을 받아 상수로 사용하고 싶을 때
	final int x;
	Sup(int x){
		this.x = x;
	}
}
public class Test80 {
	final static int x = 10; // 클래스상수
	final int y = 20;
	
	public static void main(String[] args) {
		final int z = 30; // 지역 상수
		//x = 100; // 불가능
		//y = 200; // 불가능
	}
}
