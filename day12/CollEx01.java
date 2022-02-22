package day12;

import java.util.HashMap;
import java.util.Scanner;

/*  HashMap
	id와 tel(전화번호)로 구성된 Student클래스를 만들고, 
	이름을 key로하고 Student 객체를 값으로 하는 HashMap을 작성해보세요. 
	5명 정도 HashMap에 미리 저장해놓고,  
	이름을 검색하면 id와 전화번호 출력되며, exit를 입력하면 프로그램 종료. 
*/

class Student{
	private int id;
	private String tel;
	public Student(int id, String tel){
		this.id = id; this.tel = tel;
	}
	public int getId() {
		return id;
	}
	public String getTel() {
		return tel;
	}
}
public class CollEx01 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		HashMap<String,Student> students = new HashMap<>();
		
		students.put("피카츄", new Student(0001,"0101111111"));
		students.put("라이츄", new Student(0002,"0102222222"));
		students.put("파이리", new Student(0003,"0103333333"));
		students.put("꼬북이", new Student(0004,"0104444444"));
		
		while(true) {
			System.out.print("이름을 입력하세요 >>  ");
			String word = sc.nextLine();
			if(word.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			}
			Student s = students.get(word);
			
			if(s == null) {
				System.out.println("올바른 이름을 입력하세요");
			}else{
				System.out.println("아이디 : " + s.getId() + " 전화번호 : " + s.getTel());
			}
		}
		
		
		
	}
}
