1. 람다식
	익명함수를 생성하기 위한 식으로 객체지향 언어보다 함수지향 언어에 가깝다  

	예) Runnable 인터페이스의 익명 구현 객체를 생성하는 코드  
  ```java
	// 기본적으로 클래스 만들어서 할 때  
	class Test implements Runnable{
		@Override
		public void run() { ... }
	}
	//익명 클래스
	Runnable runnable = new Runnable() { // 구현과 동시에 객체 생성해서 가능
		@Override
		public void run() { ... }
	}
	// 구현,생성 후 바로 실행
	new Runnable() {
		@Override
		public void run() { ... }
	}.run()

	// 람다로 표현하면
	Runnable runnable = () -> { ... }
	// 람다식은 "(매개변수) ->{ 실행 코드 }" 형태로 작성되는데, 
  // 마치 함수 정의 형태를 띄고 있지만 런타임시에 인터페이스의 익명 구현 객체로 생성된다.
  ```
2. 람다식 기본 문법  
	함수적 스타일의 람다식을 작성하는 방법  
	(타입 매개변수, ... ) -> { 실행문 ... }  

	- int형 매개변수 a의 값을 출력하는 람다식 작성  
		(int a) -> { System.out.println(a); };  
	- 매개변수 타입은 런타임시에 대입되는 값에 따라 자동으로 인식될 수 있기 때문에 람다식에서는 매개변수의 타입을 일반적으로 언급하지 않는다.  
		(a) -> { System.out.println(a); }  
	- 매개변수가 하나만 있다면 ()소괄호 생략할 수 있고, {}안에 실행문이 하나면 중괄호도 생략할 수 있다.  
		a -> { System.out.println(a); }  
	- 만약 매개변수가 없다면, 람다식에서 매개변수 자리가 없어지므로 빈 괄호()를 반드시 표기해주어야 한다.  
		() -> { ... }  
	- 중괄호{} 실행햐고, 결과값을 리턴해야한다면 return 문으로 결과값을 지정할 수 있다.  
		(x, y) -> { return x * y; }  
	- 중괄호 {}안에 return문만 있을 경우, 람다식에는 return 문을 사용하지 않고 바로 식만 작성 가능.  
 		(x ,y) -> x * y  

3. 타겟 타입과 함수적 인터페이스  
	인터페이스 변수명 = 람다식;  
	람다식이 대입되는 인터페이스를 람다식의 타겟타입이라고 한다.  
	1) 함수적 인터페이스 @FunctionalInterface  
		모든 인터페이스를 람다식의 타겟타입으로 사용할 수는 없다.  
		람다식이 하나의 메서드를 정의하기 때문에 두개 이상의 추상 메서드가 선언된 인터페이스는 람다식을이용해 구현객체를 생성할 수 없다.  
	2) 매개변수와 리턴값이 없는 람다식  
		우리가 만든 인터페이스를 타겟타입으로 갖는 람다식 (런타임시 객체생성됨)  
		MyFunctionalInterface my = () -> { ...... };  
		사용할 때는 변수명.메서드명()으로 사용  
		my.method();  
	3) 매개변수가 있는 람다식  
		MyFunctionalInterface my = (변수명) -> { ...... };  
		my.method(값);  
	4) 리턴값이 있는 람다식  
		매개변수도 있고, 리턴값이 있는 추상메서드를 가진 인터페이스가 있다면,  
		MyFunctionalInterface my = (변수명, ... ) -> { ... return 값; };  
		MyFunctionalInterface my = (변수명, ... ) -> { return 값; };  
		MyFunctionalInterface my = (변수명, ... ) -> 값 ; // 생략 버전  
		MyFunctionalInterface my = (변수명, ... ) -> { return 메서드(값); } ;  
		MyFunctionalInterface my = (변수명, ... ) -> 메서드(값);  // 생략 버전  
		타입변수 = my.method(값)  
4. 클래스 멤버와 로컬 변수 사용  
	람다식의 실행 블럭에는 클래스의 멤버(변수, 메서드) 및 지역변수를 사용할 수 있다.  
	클래스의 멤버는 제약사항이 없이 사용가능하지만, 지역변수는 제약사항이 따른다.  
	
	1) 클래스의 멤버 사용  
		제약 없이 사용가능하나, this 키워드를 사용할 때는 주의 필요.  
		일반적으로 익명객체 내부에서 this는 익명객체의 참조이지만, 람다식에서는 this는 내부적으로 생성되는 익명객체를 가르키는 것이 아니라 람다식을 실행한 객체를 참조한다.  

	2) 지역변수 사용  
		람다식은 주로 메서드 내부에서 작성되기 때문에, 지역 익명구현 객체를 생성시킨다고 볼 수 있다.  
		람다식에서 바깥 클래스의 필드나 메서드는 제한없이 사용할 수 있으나, 메서드의 매개변수 또는 지역변수를 사용하면, 이 두 변수는 final 특성을 가져야한다.  
		매개변수 또는 지역변수를 람다식에서 읽는 것은 허용되지만, 람다식 내부 또는 외부에서 변경할 수 있다.  

5. 표준 API의 함수적 인터페이스  
	자바 8부터는 자주 사용되는 함수적 인터페이스를 java.util.function 패키지로 제공  
	메서드의 매개변수, 생성자의 매개변수로 주로 사용되어 매개변수에 람다로 대입가능하게 하기 위해 주로 사용.  

	**[ 크게 5가지로 분류 ]**
	- Consumer  
	- Supplier  
	- Function  
	- Operator  
	- Predicate  
	
	1) Consumer 함수적 인터페이스
		accept()  

		- Consumer<T> void accept(T t)  
		- BiConsumer<T,U>  
		- DoubleConsumer / IntConsumer / LongConsumer : 기본형 받아 소비  
		- ObjDoubleConsumer / ObjIntConsumer / ObjLongConsumer : 객체와 기본형 받아 소비  

	2) Supplier  
		매개변수 X, 리턴해주는 get() 메서드  
		
		- Supplier<T> T get()  
		- BooleanSupplier boolean getAsBoolean()  
		- DoubleSupplier double getAsDouble()  
		- IntSupplier  
		LongSupplier  

	3) Function  
		매개값, 리턴값 있는 applyXXX() 메서드  
		
		- Function<T, R> R apply(T t)  
		- BiFunction<T,U,R> R apply(T t, U u)  
		- // 기본형 -> 기본형  
			기본형To기본형Function  
		- // 객체 -> 기본형  
			To기본형Function  

	4) Operator  
		매개변수와 리턴값이 있는 applyXXX() 메서드  
		매개변수 받아 연산 후 동일한 타입으로 리턴값 제공  
	
		- UnaryOperator<T> T apply(T t, T t)  
		- BiOperator<T>	T apply(T t)  
		- DoubleBinaryOperator / DoubleUnaryOperator  
		- Int ... / Int ...  
		- Long.../Long..  

	5) Predicate  
		매개변수와 boolean타입 리턴값이 있는 testXXX() 메서드  
		
		- Predicate<T> boolean test(T t) // 하나 던지는데 기본형 아닐 때  
		- BiPredicate<T, U> boolean test(T t, U u) // 두개 던질 때  
		- DoublePredicate / IntPredicate / LongPredicate  // 기본형 쓸 때  
