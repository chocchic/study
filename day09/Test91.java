package day09;
class AClass{
	int x;
	void func() {
		System.out.println("AAA");
	}
}
class BClass extends AClass{ // x, y, @func(), sub()
	int y;
	@Override
	void func() {
		System.out.println("BBB");
	}
	void sub() {
		System.out.println("subsub");
	}
}

public class Test91 {
	public static void main(String[] args) {
		AClass a = new AClass(); // x, func(sysout"AAA")
		AClass a1 = new BClass(); // x, (y), @func(sysout("BBB")), sub()
		
		System.out.println(a.x); // 자동 초기화값 0
		a.func();
		System.out.println(a1.x);
		a1.func(); // Overriding 된 애 출력
		/*
		BClass b = null;
		b = (BClass)a; // java.lang.ClassCastException;
		//컴파일은 되지만 윗줄은 BClass = b = new AClass();와 같은 의미라 에러
		b.func(); // 따라서 이 줄도 실행 X
		*/
		
		AClass a2 = new BClass();
		BClass b2 = new BClass();
		a2 = (AClass)b2;	// 형변환 생략해도 이클립스에서 에러 발생 안함
		// 다운 캐스팅
		b2 = (BClass)a2;	// 실체가 같은 타입이더라도 들고 있는 변수가 부모라면, 맞는 타입 변수에 대입할때도 형변환이 반드시 필요함.
		b2.sub();
		
		AClass a3 = new BClass();
		BClass b3 = null;
		// 참조변수가 가르키는 실제 객체가 BClass의 객체가 맞는지 판단
		if(a2 instanceof BClass) {// 맞으면 true
			b3 = (BClass)a;
		}else {
			System.out.println("형변환 불가능");
		}
		
		
	}
}
