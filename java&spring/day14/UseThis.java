package day14;

public class UseThis {
	int outterVar = 10;
	
	class Inner{
		int innerVar = 20;
		
		void method() {
			MyFuntionalInterface2 my = ()->{
				System.out.println("outter variable : " + outterVar);
				System.out.println("outter variable : " + UseThis.this.outterVar);
				System.out.println("inner variable : " + innerVar);
				System.out.println("inner variable : " + this.innerVar); // 얘를 실행하는 객체가 this 이므로 20이 출력됨
			};
			my.method();
		}//method
		
	}//Inner class
}//UseThis class
