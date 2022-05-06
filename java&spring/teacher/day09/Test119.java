package day09;

import java.util.ArrayList;
import java.util.List;

interface Test {
	public static final int NUM = 1;    // 상수
	public abstract void add(); 		// 추상 메서드 
	
	int x = 100; 	// 상수 
	int func();		// 추상메서드 
}
interface Test2 {
	void func22();
}
class AAAAA {
	int xxx; 
}
class Inter extends AAAAA implements Test, Test2 {
	@Override
	public void add() {
		System.out.println("imple~~");
	}
	@Override
	public int func() {
		return 0;
	}
	@Override
	public void func22() {
		System.out.println("func22222222");
	}
}
public class Test119 {
	public static void main(String[] args) {

		//Test t = new Test(); 
		Inter i = new Inter(); 
		i.add();
		
		// 다형성 
		// 인터페이스 타입의 변수에, 구현 클래스로 생성한 객체 담을 수 있다. (부모역활) 
		Test t = new Inter(); 
		Test2 tt = new Inter(); 
		
		
		//List arr = new ArrayList(); 
		

		
		
	}
}



