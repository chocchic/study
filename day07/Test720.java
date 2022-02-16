package day07;

import java.util.Scanner;

/*
	하루 할 일을 표현하는 클래스 Day는 다음과 같다. (수정 X)
	한달의 할일을 표현하는 MonthSchedule 클래스를 작성.
	MonthSchedule 클래스에는 Day 객체 배열과 적절한 변수, 메서드를 작성하고 실행예시처럼 입력,보기, 끝내기 등의 기능을 작성하세요.
	-> 생성자, input(), view(), finish(), run() 메서드 만들기
	# 실행예시 #
	이번달 스케쥴 관리 프로그램
	할일 (입력 : 1, 보기 : 2 끝내기 : 3) >> 1(사용자가 입력)
	날짜(1~28) >> 1(사용자가 입력)
	할 일 >> 자바 공부(사용자가 입력)
	
	할일 (입력 : 1, 보기 : 2 끝내기 : 3) >> 2(사용자가 입력)
	날짜(1~28) >> 1(사용자가 입력)
	1일의 할 일은 자바 공부 (할 일이 없을 경우 : 1일의 할 일은 없습니다.)
	
	할일 (입력 : 1, 보기 : 2 끝내기 : 3) >> 3(사용자가 입력)
	프로그램 종료. 
*/
class Day{
	private String work;
	public void set(String work) { this.work = work;}
	public String get() { return work; }
	public void show() {
		if(work == null) System.out.println("없습니다.");
		else System.out.println(work + "입니다.");
	}	
}

class MonthSchedule{
	Day[] month;
	int lastday;
	Scanner sc = new Scanner(System.in);
	
	MonthSchedule(int n){
		lastday = n;
		month = new Day[n];
		for(int i = 0; i < n; i++) {
			month[i] = new Day();
		}
	}
	
	void input() {
		System.out.println("날짜(1~" +lastday +") >> ");
		int dd = Integer.parseInt(sc.nextLine());
		System.out.println("할 일 >>");
		month[dd-1].set(sc.nextLine());
	}
	
	void view( ) {
		System.out.println("날짜(1~" +lastday +") >> ");
		int dd = Integer.parseInt(sc.nextLine());
		if(month[dd-1].get() != null) {
			System.out.println(dd + "의 할 일은 " + month[dd-1].get());
		}else {
			System.out.println(dd + "의 할일은 없습니다.");
		}
	}
	
	boolean finish() {
		System.out.println("프로그램 종료");
		return false;
	}
	
	void run() {
		boolean running = true;
		System.out.println("이번달 스케쥴 관리 프로그램");
		while(running) {
			System.out.println("할일 (입력 : 1, 보기 : 2 끝내기 : 3) >> ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				input();
				break;
			case 2:
				view();
				break;
			case 3:
				running = finish();
				break;
			default:
				System.out.println("올바른 값을 입력하세요");
			}
		}
	}
}
public class Test720 {
	public static void main(String[] args) {
		// main 작성 완료.
		MonthSchedule fab = new MonthSchedule(28); // 2월은 28일  
		fab.run(); 
	}
}
