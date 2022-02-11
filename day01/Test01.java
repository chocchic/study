package day01; //패키지 지정하는 명령어 없으면 빨간 줄나옴 항상 있어야한다.  

class Test022{ // public없이 클래스 생성 가능

}

public class Test01 { // 클래스
	/*
		클래스 명명 규칙 : 첫 글자는 대문자. 띄어쓰기 불가능. 특수기호 '_', '$'만 사용 가능.
						숫자로 시작 불가능. 대소문자 구분. Num != num
						다른 클래스 첫 글자가 소문자일 때 실행 안되는 API도 있으니 유의
	*/
	// 변수, 상수, 메서드 구현

	// 빨간 색은 의미를 가지고 있는 keyword
	// 그냥 글씨는 메서드. ex)main
	public static void main(String[] argc){

		// 메인 메서드 : 프로그램 시작시 가장 먼저 자동으로 실행되는 메서드

		// 코드 작성 영역
		System.out.println("hello"); //출력문
		System.out.println("ciao!!");

		// 주석 : 실행하고 싶지 않은 코드나 메모시 사용
		/*
		 * 여러줄 주석 가능
		*/
		// 실행 : ctrl + f11
		// 주석 : ctrl + /


	}
	
}
