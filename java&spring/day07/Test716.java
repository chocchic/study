package day07;

import java.util.Scanner;

/*
	Grade클래스를 작성해보세요.
	3과목의 점수를 입력받아 Grade 객체를 생성하고,
	성적 평균을 출력하는 main() 메서드와 실행 예시는 아래와 같습니다.
	콘솔 출력 예 >>
		수학, 과학, 영어 점수를 입력하세요.
		수학 >> 90 (<- 사용자가 입력)
		과학 >> 88
		영어 >> 86
		평균은 91
*/
class Grade{
	int math;
	int sci;
	int eng;
	
	Grade(int math, int sci, int eng){
		this.math = math;
		this.sci = sci;
		this.eng = eng;
	}
	
	int getAvg() {
		return (math+sci+eng)/3;
	}
}
public class Test716 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("수학, 과학, 영어 점수를 입력하세요.");
		System.out.println("수학 >> ");
		int math = Integer.parseInt(sc.nextLine());
		System.out.println("과학 >> ");
		int sci = Integer.parseInt(sc.nextLine());
		System.out.println("수학 >> ");
		int eng = Integer.parseInt(sc.nextLine());
		
		Grade me = new Grade(math, sci,eng);
		//System.out.println("평균은 " + me.getAvg());
	}
}
