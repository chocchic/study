package day07;
class Circle2{
	int radius;
	Circle2(){}
	Circle2(int radius){
		this.radius = radius;
	}
//	Circle2 getCircleObj() {
//		return this;
//	}
	void getCircleObj() {
		System.out.println("getcircleobj : " + this);
	}
	
}
public class Test78 {
	int x = 10;
	void func(int x) {
		//this.x = 인스턴스 변수 x  
		// x = 매개변수 x
		this.x = x;
	}
	public static void main(String[] args) {
		Circle2 c = new Circle2();
		System.out.println("main c : "+c);
		//System.out.println(c.getCircleObj());
		
		Circle2 c2 = new Circle2();
		System.out.println("main c2 : "+c);
		c2.getCircleObj();
		
	}
	
}
