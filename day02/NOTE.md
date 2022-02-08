1. 입력문
	1) System.in : 키보드 장치를 직접 제어, 키 입력을 받는 표준 입력 스트림 객체
			키보드 입력 > System.in 입력스트림 > 바이트데이터 > Scanner > 형변환된 데이터 > 자바 응용프로그램
	
	2) Scanner : 자바 패키지에서 제공하는 클래스, 원하는 타입으로 변환해줌
		1. import 문 : Scanner 클래스가 있는 자바 제공 패키지를 여기서 사용하겠다
			ex ) import java.util.Scanner;
			+ 클래스밖 패키지 밑에 선언
		2. Scanner 객체 생성
			Scanner sc = new Scanner(System.in);
		3. 입력받기
			sc.nextLine(); // 문자열 String 타입으로 return. 입력 받고 싶은 만큼 계속 사용 가능.
			ex) String 변수명 = sc.nextLine();
		4. Scanner 객체 닫기
			sc.close();
