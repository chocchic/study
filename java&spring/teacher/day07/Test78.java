package day07;

class Circle2 {
	int radius; 
	Circle2() {}
	Circle2(int radius){
		this.radius = radius;
	}
	void getCircleObj() {
		System.out.println("getCircleObj : " + this);
		//return this;
	}
}
public class Test78 {
	public static void main(String[] args) {

		Circle2 c = new Circle2(); 
		System.out.println("main c : " + c);
		c.getCircleObj();
		
		Circle2 d = new Circle2(); 
		System.out.println("main d : " + d);
		d.getCircleObj();
	
	}
	/*
	int x = 10; 
	
	void func(int x) {
		// thi.x = 인스턴스변수 x
		// x = 매개변수 x 
		this.x = x; 
	}
	*/
}
