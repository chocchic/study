## 4. 변수 variable
	: 하나의 값을 저장할 수 있는 공간.

	1) 변수의 타입 : 처음에 만들어질 때 지정한 데이터의 타입만 저장 가능.
	
	2) 변수의 명명규칙 : 첫 글자는 소문자로. 띄어쓰기 불가능. 특수기호'_', '$'만 사용가능
			  키워드 사용 불가, 대소문자 구분, 숫자로 시작 불가능. 의미있는 이름 지향
			  블럭 {}안에 같은 이름 X.
			  currentPosition 카멜표기법
			  current_position 스네이크표기법
			  iCurrent or fCurrent... 데이터 타입의 약자를 넣은 표기법

## 5. 데이터 타입
	0) 메모리 단위
		1 bit = 0 or 1
		1 byte = 8 bit
		bit < byte < KB < MB < TB < PB < EB < ZB < YB

	- 문자 : char
	- 숫자 : 정수 : byte, int, short, long 
	         실수 : float, double
	- 논리 : boolean (true, false) true = 1, false = 0 자바는 안됨.
	
	1) 기본형 primitive type : 8가지
		논리형 : boolean - 1 byte : true, false 둘 중 하나의 값을 가진다.
		문자형 : character - 2 byte : 유니코드 체제이므로 2 byte. 한 글자만 저장 가능. (문자열은 String 클래스 사용)
		           ' ' 로 묶어서 표현 ex) 'a'
		정수형 : 음수 / 양수(소수 점 없는 수)
			byte 	- 1 byte : -128 ~ 127
			short 	- 2 byte : -32768 ~ 32767
			int	- 4 byte : 약 -21억 ~ 21억
			long	- 8 byte : 그냥 많다.
		실수형 : 소수점
			float	- 4 byte : 소수점 8자리
			double	- 8 byte : 소수점 16자리
		문자 : 'a'
		문자열 : "abc" "a"
		숫자 : 숫자 ex) 10, 100, 3.14

	2) 참조형 reference type : 클래스, c언어의 포인터와 비슷
		배열에 대한 레퍼런스, 클래스에 대한 레퍼런스, 인터페이스에 대한 레퍼런스 값
		문자열 : String <- S가 대문자이기 때문에 클래스로 구현되었다는 것만 알면 됨.
	
## 6. 변수의 선언과 사용
	1) 변수 선언 : 이런 타입의 변수를 만들거야
	
		타입 변수명;
		int num;
	
	2) 선언 후 대입
		변수명 = 값;
		num = 10;
	
	3) 선언 + 대입
		타입 변수명 = 값;
		int num = 10;

	4) 대입연산자 '='
		오른 편의 모든 연산을 마친 후 결과를 왼 편 변수에 담는다.
		
	5) 접미사
	
		int a = 100; <- int 4 byte
		long b = 100; <- long 8 byte
		=> long b = 100L;
		double d = 10.123; => float d = 10.123F; float을 float에 넣을 땐 F 생략 가능. 
	
	6) 기본값
		boolean : false
		char : '\u0000'(쓸일 없다)
		int 등 : 0, 0.0
		참조형 : null

## 7. 출력문
	1) 종류	
		System.out.println(); : 엔터기능 O
		System.out.print(); : 엔터기능 X
		system.out.printf(); : 엔터기능 X, 서식문자 이용

	2) 출력문 내 연산
		숫자 + 숫자 ==> 연산 결과를 출력
		"문자열" + "문자" ==>문자로 연결된 문자열 출력
		"문자열"+숫자 ==> 숫자도 문자 취급하여 문자로 연결된 문자열 출력
		'문자'+ 숫자 ==> 문자의 아스키코드값 + 숫자의 결과값 출력
	
	3) 이스케이프 문자 : 출력문 안에서 어떠한 기능을 가지고 있는 특수 문자
		\n	: 엔터기능(개행)
		\t	: 탭 간격 주기
		\"	: 쌍따옴표 자체를 문자열로 출력 ex) println("hello\"여러분\"");
		\'	: 홑따옴표 자체를 문자열로 출력
		\\	: 역슬래쉬 자체를 문자열로 출력
	
	() 소괄호
	{} 중괄호
	[] 대괄호

## 8. 형변환 Casting, type conversion, converting
	boolean 타입을 제외한 다른 타입들을 변환시키는 것.
	
	(타입)변수 

	char < int< long  float < double

	1) 다운캐스팅 / 디모션 / 강제 형변환 : 큰 타입 -> 작은 타입 (형변환 공식을 반드시 작성)
		
	2) 업캐스팅 / 프로모션 / 자동 형변환 : 작은 타입 -> 큰 타입 (형변환 공식을 생략가능)
