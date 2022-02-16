package day07;
// 캡슐화를 활용 
class Person2 {
	private int age = 1; 
	public void setAge(int age) {
		if(age > 0 && age < 150) {
			this.age = age; 
		}else {
			System.out.println("나이가 이상해요....");
		}
	}
	public int getAge() {
		return age; 
	}
}

public class Test84 {
	public static void main(String[] args) {
		
		Person2 p = new Person2(); 
		//p.age = 250;
		p.setAge(20);
		System.out.println(p.getAge());
		
		
		// 연습 
		// 이름, 나이, 혈액형, 키, 주소, 전화번호, 주민번호에 해당하는 변수들과 
		// 	get/set메서드 만들기 (캡슐화)
		
		
		
		
		
		
		
		
		
		
	}
}










