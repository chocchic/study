package day15;

public class Person {
	public enum Gender {MALE, FEMALE}
	public enum City {Seoul, Pusan}
	
	private String name;
	private int age;
	private Gender gender;
	private City city;
	
	public Person(String name, int age, Gender gender) {
		this.name = name; this.age = age; this.gender = gender;
	}	
	public Person(String name, int age, Gender gender, City city) {
		this.name = name; this.age = age; this.gender = gender; this.city = city;
	}
	public String getName() {return name;}
	public int getAge() {return age;}
	public Gender getGender() {return gender;}
	public City getCity() {return city;}
}
