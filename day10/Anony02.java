package day10;
class Person{
	void wake() {
		System.out.println("7시에 기상");
	}
}
class Anony{
	// 변수의 초기값으로 익명 클래스 사용
	Person p = new Person() {
		void work() {
			System.out.println("출근");
		}
		@Override
		void wake() {
			System.out.println("6시 기상");
			work();
		}
	};
	
	void func() {
		// 지역 변수의 초기값으로 익명클래스 사용
		Person localP = new Person() {
			void walk() {
				System.out.println("산책");
			}
			
			@Override
			void wake() {
				System.out.println("7시 반에 일어납니다.");
				walk();
			}
		};
		localP.wake();
	}
	// 매개 변수에 익명 클래스를 인자로 전달하는 방식
	void func2(Person p) {
		p.wake();
	}
}
public class Anony02 {
	public static void main(String[] args) {
		Anony anony = new Anony();
		//anony.p.wake();
		anony.func();
		anony.func2(new Person() {
			void study() {
				System.out.println("공부");
			}
			@Override
			void wake() {
				System.out.println("6시 반에 일어나야됨");
				study();
			}
		});
	}
}
