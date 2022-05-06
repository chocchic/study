package day07;

class Person{
	private String name;
	private int age;
	
	public void setName(String name) { this.name = name; }
	public String getName() { return name;}
	public void setAge(int age) { this.age = age; }
	public int getAge() { return age;}
}

public class Test713 {
	public static void main(String[] args) {
		Person p = new Person();
		//p.name = "asd"; // error
		p.setName("asd");
		System.out.println(p.getName());
		p.setAge(10);
		System.out.println(p.getAge());
	}
}
