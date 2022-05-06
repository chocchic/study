package day05;

// #1. 클래스 만들기. 
class Person {
	// 속성 = 변수 
	String name = "hello"; 
	int age = 10; 
	// 기능 = 메서드 
	void work() {
		System.out.println("열심히 일한다~~~~");
	}
	void eat() {
		System.out.println("밥먹기~~ 배고프다~~~");
	}
}
public class Test51 { // 클래스 
	// 변수 작성 가능. 
	// 메서드 : 메인메서드 : 프로그램 시작시 가장 먼저 실행되는 메서드. 
	public static void main(String[] args) {
		// 프로그램 시작 지점. 
		
		// #2. 객체 생성 
		Person p = new Person(); 
		Person p1 = new Person(); 
		System.out.println(p);
		
		// #3. 사용 
		System.out.println(p.name);
		System.out.println(p.age);
		System.out.println(p1.name);
		System.out.println(p1.age);
		System.out.println("---------------");
		
		p.name = "피카츄";
		p.age = 100; 
		
		System.out.println(p.name);
		System.out.println(p.age);
		System.out.println(p1.name);
		System.out.println(p1.age);
		
		// 메서드 실행시키기 
		p.work();
		p.eat();
		
		
		
		
		
		
		
	
	}
}










