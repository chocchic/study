package day09;
class Person{}
class Student extends Person{}
class Teacher extends Person{}
class Professor extends Teacher{}

public class Test92 {
	static void print(Person p) { // p = new Student(); = Teacher();... 뭐가 올지 모르겠지만 자식이라면 다 받아주겠다는 의미
		if(p instanceof Person) System.out.println("Person");
		if(p instanceof Student) System.out.println("Student");
		if(p instanceof Teacher) System.out.println("Teacher");
		if(p instanceof Professor) System.out.println("Professor");
	}
	public static void main(String[] args) {
//		print(new Person());
		print(new Student()); // Person, Student 부모가 Person이니까 Person개체도 맞음
//		print(new Teacher());
//		print(new Professor()); // Person, Teacher, Professor 부모의 클래스까지 다 출력
		
	}
}
