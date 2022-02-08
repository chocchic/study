package day02;

public class Test16 {
	public static void main(String[] args) {
		int a = 20;
		if(a > 10) {// 조건문이 없으면 바로 오류 필수 기입.
			System.out.println("a는 10보다 크다");
		}
		// if문이 끝나고 나면 뒤에 코드 실행
		boolean b = true;
		if(b) { // b는 boolean type이고 true를 의미하므로 true
			System.out.println("true");
		}
				
		if(a-10 > 10) { // 연산자가 먼저이므로 a -10 = 10 10은 10보다 클 수 없으므로 false
			System.out.println("hello");
		}
		
		int score = 70;
		// 점수가 60점 이상이면 "합격", 아니면 "불합격" 출력
		if(score >= 60) {
			System.out.println("합격");
		}
		if(score < 60) { // false이므로 skip
			System.out.println("불합격");
		}
		
		//if-else로 축약. 조건문 하나면 충분!
		if(score >=60) {
			System.out.println("합격");
		}else{
			System.out.println("불합격");
		}		
	}
}
