package day12;

import java.util.ArrayList;
import java.util.Scanner;

/*
 	하나의 학생 정보를 나타내는 StudentClass에는 이름, 학과, 학번, 학점평균을 저장하는 필드가 있다. 
 	학생마다 StudentClass 객체를 생성하고 4명의 학생정보를 
 	ArrayList 에 저장한 후, 모든 학생의 정보를 출력, 
 	학생이름을 입력받아 해당 학생의 학점 평균을 출력하는 프로그램을 작성하세요. 
 	실행예시:
 		학생이름, 학과, 학번, 학점 평균 입력하세요.
 		>> 아이언맨,빅데이터,1,4.25  (사용자 입력)
 		>> 데드풀,안드로이드,2,3.9
 		>> 호크아이,모바일,3,4.1
 		>> 헐크,웹개발,4,3.5
 		------------------------------------------
 		이름:아이언맨
 		학과:빅데이터
 		학번:1
 		학점평균:4.25
 		------------------------------------------
 		이름:데드풀
 		학과:안드로이드
 		학번:2
 		학점평균:3.9
 		------------------------------------------
 		이름:호크아이
 		학과:모바일
 		학번:3
 		학점평균:4.1
 		------------------------------------------
 		이름:헐크
 		학과:웹개발
 		학번:4
 		학점평균:3.5
 		------------------------------------------
 		학생 이름 >> 아이언맨   (사용자 입력)
 		아이언맨, 빅데이터, 1, 4.25
 		학생 이름 >> 호크아이 
 		호크아이, 모바일, 3, 4.1
 		학생 이름 >> 그만
 		프로그램 종료! 
*/
class StudentClass{
	private String name, dept;
	private int id;
	private double score;
	
	public StudentClass(String name, String dept, int id, double score) {
		this.name = name; this.dept = dept; this.id = id; this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public String getDept() {
		return dept;
	}
	public int getId() {return id;}
	public double getScore() {return score;}
}
public class CollEx07 {
	public static void main(String[] args) {
		ArrayList<StudentClass> students = new ArrayList<StudentClass>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("학생이름, 학과, 학번, 학점 평균 입력하세요.");
		for(int i = 0;i<4;i++) {
			System.out.print(">>");
			String input = sc.nextLine();
			String[] inputSlice = input.split(",");
			int id = Integer.parseInt(inputSlice[2]);
			double score = Double.parseDouble(inputSlice[3]);
			students.add(new StudentClass(inputSlice[0],inputSlice[1],id,score));
		}
		for(StudentClass ss : students) {
			System.out.println("-----------------------------");
			System.out.println("이름:"+ss.getName()+"\n학과:"+ss.getDept()+"\n학번:"+ss.getId()+"\n학점평균:"+ss.getScore());
		}
		System.out.println("-----------------------------");
		while(true) {
			System.out.print("학생 이름 >> "); String name = sc.nextLine();
			if(name.equals("그만")) {
				System.out.println("프로그램 종료!!");
				break;
			}else {
				for(StudentClass ss : students) {
					if(ss.getName().equals(name))
					System.out.println(ss.getName()+","+ss.getDept()+","+ss.getId()+","+ss.getScore());
				}
			}
		}
	}
}
