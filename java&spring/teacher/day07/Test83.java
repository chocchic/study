package day07;

class Person {
	// 캡슐화 
	private String name; 
	private int age; 
	//Person() {} 
	public void setName(String name) { // setter 또는 set메서드
		this.name = name; 
	}
	public String getName() {
		return name; 
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
}
public class Test83 {
	public static void main(String[] args) {

		Person p = new Person(); 
		//p.name = "pikapika";
		p.setName("pikapika");
		System.out.println(p.getName());
		p.setAge(10);
		System.out.println(p.getAge());
		
		
		
		
		
		
	}
}



