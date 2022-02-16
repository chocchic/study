0. static  
	static 메서드는 객체 생성 없이 바로 사용 가능.  
	-> static 메서드는 static 멤버만 접근 가능(인스턴스 X)  
	-> static 메서드에서 this 키워드 사용할 수 없음  

1. final
	1)final 클래스  
		클래스 앞에 붙으면, 상속할 수 없음을 지정함.  
		-> 부모가 될 수 없다.  
		```java
		final class FinalClass {   }
		```

	2)final 메서드   
		메서드 앞에 붙으면, 오버라이딩을 할 수 없음을 선언함  
		```java
		final int FinalMethod { ... }
		```

	3)final 메서드  
		변수 앞에 붙으면, 상수가 된다.  
		상수는 한번 초기화 되면 값을 변경할 수 없다.  
		final int row = 10; // 상수선언  
		row = 20;  

2. 상속 inheritance  
	1) 클래스와 클래스 사이(1:1)의 부모자식 관계를 만드는 것  
	2) 자바 다중상속 X  
	3) 상속은 부모의 변수, 메서드를 물려받는 것  
	4) 상속시, 생성자와 초기화 블럭은 제외  
	5) 상속 키워드 : extends  
	6) 상속을 받으면 부모의 멤버들을 자식이 담게된다.  
		-> 자식은 부모의 것과 함께 자식만의 변수와 메서드를 갖게 된다.  
	7) 필요한 이유 : 코드 중복을 제거, 유지보수 편리성, 소프트웨어 생산성 향상  
	
	부모클래스 = super = parent = base  
	자식클래스 = sub = child = derived  
	
	class Object {}			// 조상님  
	class Parent {}			// 할미할비 : X  
	class Child extends Parent {}		// 엄마아빠 : X, Y  
	class Child2 extends Parent {}	// 이모나 삼촌(남) : X,W  
	class GrandChild extends Child {}	// 아들, 딸 : X,Y,Z  

	8) Object 클래스  : 모든 클래스의 조상  
	9) package : 프로그램에서 폴더/디렉토리를 말함.  
		보통 패키지는 3수준까지 내려가는 폴더 이름을 작성  

		java.awt.color.클래스  
	10) java.lang : 가장 기본적인 package  
