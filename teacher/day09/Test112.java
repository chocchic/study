package day09;

class Person { }
class Student extends Person { }
class Teacher extends Person { }
class Professor extends Teacher { } 

public class Test112 {
	
	static void print(Person p) { // p = new Student(); = Teacher(); 
		if(p instanceof Person) { System.out.println("Person!!"); }
		if(p instanceof Student) { System.out.println("Student!!"); }
		if(p instanceof Teacher) { System.out.println("Teacher!!"); }
		if(p instanceof Professor) { System.out.println("Professor!!"); }
	}	
	
	public static void main(String[] args) {

		//print(new Person()); 
		//print(new Student());
		//print(new Teacher());
		print(new Professor());
		
		
	}
}
