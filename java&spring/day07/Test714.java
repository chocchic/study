package day07;
// 캡슐화 활용
class Person2{
	private int age = 1;
	public void setAge(int age) {
		if(age > 0 && age < 150) {
			this.age = age;
		}else {
			System.out.println("나이가 이상");
		}
	}
	public int getAge() {
		return age;
	}
}

class Person3{
	private String name;
	private int age;
	private String blood;
	private int height;
	private String address;
	private boolean isWoman;
	private int telephone;
	private int identity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getblood() {
		return blood;
	}
	public void setblood(String blood) {
		this.blood = blood;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isWoman() {
		return isWoman;
	}
	public void setWoman(boolean isWoman) {
		this.isWoman = isWoman;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getIdentity() {
		return identity;
	}
	public void setIdentity(int identity) {
		this.identity = identity;
	}
		
}
public class Test714 {
	public static void main(String[] args) {
		Person2 p = new Person2();
		p.setAge(20);
		System.out.println(p.getAge());
		
		// 연습
		// 이름, 나이, 혈액형, 키, 주소, 전화번호, 주민번호에 해당하는 변수들과
		// get/set메서드 만들기(캡슐화)
	}
}
