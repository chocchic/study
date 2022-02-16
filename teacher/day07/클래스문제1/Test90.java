package day07;
/*
	하루할일을 표현하는 클래스 Day는 아래와 같다. (수정할것 없음. 그대로 사용) 
	한달의 할일을 표현하는 MonthSchedule 클래스를 작성하세요. 
	MonthSchedule 클래스에는 Day 객체 배열과 적절한 변수, 메서드를 작성하고
	실행예시처럼 입력, 보기, 끝내기 등의 기능을 작성하세요. 
	-> 생성자, input(), view(), finish(), run() 메서드 만들기. 
	#실행예시#
	이번달 스케쥴 관리 프로그램
	할일(입력:1, 보기:2, 끝내기3) >>   1 (사용자가 입력) 
	날짜(1~28)? >>  1 (<-사용자 입력) 
	할일? >>  자바공부 (<-사용자 입력)
	
	할일(입력:1, 보기:2, 끝내기3) >>   2  
	날짜(1~28)? >>  1  
	1일의 할 일은 자바공부입니다.    (할일이 없을경우 : 1일의 할 일은 없습니다.) 
	
	할일(입력:1, 보기:2, 끝내기3) >> 3
	프로그램 종료.
*/
class Day {
	private String work; // 하루의 할일을 저장할 변수 
	public void set(String work) { this.work = work; }
	public String get() { return work; }
	public void show() {
		if(work == null) System.out.println(" 없습니다.");
		else System.out.println(work + "입니다.");
	}
}
class MonthSchedule {
	
}
public class Test90 {
	public static void main(String[] args) {

		// main 작성 완료.
		//MonthSchedule fab = new MonthSchedule(28); // 2월은 28일  
		//fab.run(); 
		
		
	}
}
