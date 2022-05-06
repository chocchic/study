package day05;
class Person{
	// 속성 = 변수
	int age =10;
	String name = "hello";
	
	void work() {
		System.out.println("일하기");
	}
	
	void eat() {
		System.out.println("밥먹기");
	}
}

public class Test51{//클래스
	public static void main(String[] args) {
		// 프로그램 시작 지점
		
		//#2. 객체 생성
		Person p = new Person();
		Person p1 = new Person();
		System.out.println(p);
		
		//#3. 사용 
		System.out.println(p.name);
		System.out.println(p.age);
		System.out.println(p1.name);
		System.out.println(p1.age);
		
		p.name = "피카튜";
		p.age = 100;
		
		System.out.println(p.name);
		System.out.println(p.age);
		System.out.println(p1.name);
		System.out.println(p1.age);
		
		// 메서드 실행
		p.work();
		p.eat();
	}
}