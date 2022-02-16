package day08;

class Super1 { // func() 
	void func() {
		System.out.println("super");
	}
	
}
class Sub1 extends Super1{ // func()
	@Override
	void func() {
		super.func();
		System.out.println("subsub");
	}
	
}

public class Test106 {
	public static void main(String[] args) {
		
		Sub1 sub = new Sub1(); 
		sub.func();
		
		
	}
}
