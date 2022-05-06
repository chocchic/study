package day12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
	이름과 학점(4.5 만점)을 5개 입력받아 HashMap에 저장하고, 
	장학생 선발 기준을 입력받아 장학생 명단을 출력하세요. 
	실행 예시 :
		자바 장학금관리 시스템입니다. 
		이름과 학점 >> 토르 3.1  (사용자 입력)
		이름과 학점 >> 블랙위도우 4.0
		이름과 학점 >> 데드풀 2.8
		이름과 학점 >> 아이언맨 4.3
		이름과 학점 >> 호크아이 3.8
		장학생 선발 학점 기준 입력 >> 3.2 
		장학생 명단 : 블랙위도우 아이언맨 호크아이
*/
class Scholarship{
	private String name;
	Scanner sc;
	HashMap<String, Double> student;
	
	public Scholarship(String name) {
		this.name = name;
		sc = new Scanner(System.in);
		student = new HashMap<>();
	}
	
	public void read() {
		System.out.println(name + "관리 시스템입니다.");
		for(int i = 0; i<5; i++) {
			try {
				System.out.print("이름과 학점 >> ");
				String s = sc.nextLine();
				String[] sslice = s.split(" ");
				double d = Double.parseDouble(sslice[1]);
				student.put(sslice[0], d);
			}catch(Exception e) {
				System.out.println("입력 안됨");
				i--;
			}
		}
	}
	
	public void select() {
		System.out.print("장학생 선발 학점 기준 입력 >> ");
		double limit = Double.parseDouble(sc.nextLine());
		System.out.print("장학생 명단 : ");
		Set s = student.entrySet();
		Iterator it = s.iterator();
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			if((double)e.getValue() >= limit) {
				System.out.print(e.getKey() + " ");
			}
		}
	}
}
public class CollEx09 {
	public static void main(String[] args) {

		// main 작성완료, 주석해제해서 실행 
		Scholarship sch = new Scholarship("자바 장학금"); 
		sch.read(); 
		sch.select(); 
		
		
	}
}
