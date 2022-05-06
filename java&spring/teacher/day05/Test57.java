package day05;

class Tv5 {
	boolean power = false;
	int ch = 1, vol = 0; 
}

public class Test57 {

	Tv5 tv; 
	Test57 test; 
	
	public static void main(String[] args) {

		Test57 a = new Test57(); 
		System.out.println(a.test); // null
		if(a.test != null) {
			System.out.println(a.test.tv); // tv의 주소를 원해~
		}else {
			a.test = new Test57(); 
			System.out.println(a.test.tv);
		}
		
		
		
	}
}
