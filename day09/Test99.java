package day09;

import java.util.ArrayList;
import java.util.List;

interface Test{
	public static final int NUM = 1;	// 상수
	public abstract void add();			// 추상 메서드
	
	int x = 100;// 상수
	int func(); // 추상 메서드 public static final 생략 가능
}
interface Test2 {
	void func22();
}
class AAAAA{
	int xxx;
}
class Inter extends AAAAA implements Test,Test2{

	@Override
	public void add() {
		System.out.println("imple~");
	}

	@Override
	public int func() {
		return 0;
	}

	@Override
	public void func22() {
		// Test2도 상속받았으니까 구현
		System.out.println("Test2");
	}
	
}
public class Test99 {
	public static void main(String[] args) {
//		Test t = new Test(); // interface는 객체로 못만듬
		Inter i = new Inter();
		i.add();
		System.out.println(i.xxx); // 구현은 여러개가 가능하지만 상속은 단 하나만 가능		
		
		// 다형성
		// 인터페이스 타입의 변수에 구현클래스로 생성한 객체를 담을 수 있다. (부모 역할)
		// 대신 그 부모가 아는 선에서만 사용 가능
		Test t = new Inter();
//		t.func2() // 그래서 불가능
		Test2 tt = new Inter();
		AAAAA ttt = new Inter();
		
		//List는 interface
//		List<E> arr = new ArrayList(); // 가능
		
	}
}
