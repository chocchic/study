package day09;

abstract class Super {
	abstract void func();
	abstract void func1();
	// 일반 변수들과 메서드들도 작성 가능. 
	int a = 10; 
	void aaa() {
		 //....
	}
}
class Sub extends Super {
	@Override
	void func() {
		// 나는 이것만 
	}
	@Override
	void func1() { }	
}
public class Test117 {
	public static void main(String[] args) {

		//미완성 형태의 추상클래스로는 객체 생성 불가!!!!
		//Super s = new Super(); 
		
		Sub sub = new Sub(); 
		sub.func();
		sub.aaa();
		
		
		
		
		
		
		
	}
}
