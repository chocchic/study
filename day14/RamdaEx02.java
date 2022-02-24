package day14;
class Test implements MyFuntionalInterface2 {
	@Override
	public void method() {
		System.out.println("ehahahahah");
	}
	
//	Test t = new Test();
}
public class RamdaEx02 {
	public static void main(String[] args) {
		UseThis useThis = new UseThis(); // 외부클래스 객체 생성
		UseThis.Inner inner = useThis.new Inner(); // 외부참조변수로 내부 객체 생성
		inner.method();
	}
}
