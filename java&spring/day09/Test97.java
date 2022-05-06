package day09;

abstract class Super{
	abstract void func();
	abstract void func1();
	// 일반 변수들과 메서드들도 작성 가능
	// 추상 메서드 func때문에 밑애 있는애들은 상속받아야만 사용사능
	int a = 10;
	void aaa() {
		//...
	}
}
class Sub extends Super{
	@Override
	void func() {
		// 내가 개발해야 하는것 구현
		
	}
	@Override
	void func1() {
		// TODO Auto-generated method stub
		// 다른사람이 구현해야 한다면 일단 생성만하면됨
	}
}
public class Test97 {
	public static void main(String[] args) {
		// 미완성 형태의 추상클래스로는 객체 생성 불가
//		Super s = new Super() {	};
		Sub sub = new Sub();
		sub.func();
		sub.aaa();
	}
}
