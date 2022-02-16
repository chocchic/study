package day08;
class A1{
	A1(){
		System.out.println("생성자 A");
	}
	A1(int x){
		System.out.println("생성자 A-param");
	}
}
class B1 extends A1{
	B1(){
		//super(); // 원래 이렇게 적어주는게 맞다
		System.out.println("생성자 B");
	}
	B1(int x){
		super(x); // 이게 없으면 A의 매개변수가 없을때의 기본 생성자를 출력
		System.out.println("생성자 B-param");
	}
}


public class Test85 {
	public static void main(String[] args) {
		// A1의 기본생성자가 없으면 error
		// 상속해줄 클래스의 경우 기본생성자가 필수다.
		B1 b = new B1(5);
	}
}
