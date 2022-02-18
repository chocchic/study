package day10;
interface MyInter{
	void func();
}

class Test{
	void method(MyInter inter) {
		inter.func();
	}
}
public class Anony01 {
	public static void main(String[] args) {
		
		// 바로 정의하여 객체 생성해 실행
		new MyInter() {
			@Override
			public void func() {
				System.out.println("MyInter func 오버라이드");
			}
		}.func();
		
		Test t= new Test();
		t.method(new MyInter() {
			
			@Override
			public void func() {
				System.out.println("MyInterfunc override");
			}
		});
	}
}
