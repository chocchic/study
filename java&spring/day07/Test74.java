package day07;

public class Test74 {
	
	double d;
	
	Test74(){
		System.out.println("생성자 호출");
		d = Math.random();
	}
	Test74(double a){
		d = a;
	}
	
	public static void main(String[] args) {
		Test74 test = new Test74();
		Test74 test2 = new Test74(3.1147);
		System.out.println(test.d);
		System.out.println(test2.d);
	}
}
