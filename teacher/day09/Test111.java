package day09;

class AClass {			// x, func() 
	int x; 
	void func() {
		System.out.println("AAA");
	}
}
class BClass extends AClass { // x, y, @func(), sub()
	int y; 
	@Override
	void func() {
		System.out.println("BBB");
	}
	void sub() {
		System.out.println("subsub");
	}
}
public class Test111 {
	public static void main(String[] args) {

		AClass a = new BClass(); 
		//AClass a = new AClass(); 
		BClass b = null; 
		
		// 참조변수가 가르키는 실제객체가 BClass의 객체가 맞는지 판단하고
		if(a instanceof BClass) { // 맞으면 true
			System.out.println("형변환 가능~~");
			b = (BClass)a; // 실행해라 
		}else {
			System.out.println("형변환 불가능!!");
		}
		
		/*
		AClass a = null; 
		BClass b = new BClass(); 
		BClass b2 = null; 
		
		a = (AClass)b; 	// 업캐스팅 : 형변환 생략해도 이클립스에서 에러 발생 안함.
		b2 = (BClass)a; // 다운캐스팅 : 실체가 같은 타입이더라고 들고있는 변수가 부모라면,
						// 맞는 타입 변수에 대입할때도 형변환이 반드시 필요함. 
		b2.sub();
		///////////////////////////////
		
		
		AClass a = new AClass(); 
		BClass b = null; 
		
		b = (BClass)a; // java.lang.ClassCastException: 
		// BClass b = new AClass(); 
		b.func();
		////////////////////////////////
		
		AClass a = null; 
		BClass b = new BClass(); 
		a = (AClass)b; 
		a.func();
		//a.sub();
		///////////////////////////////
		
		AClass a = new AClass();  // x, func(syso"AAA")
		AClass a1 = new BClass(); // x, @func(syso"BBB"), [ y, sub() ]
		
		System.out.println(a.x);
		a.func();
		
		System.out.println(a1.x);
		a1.func();
		*/
		
	}
}











