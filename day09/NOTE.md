1. 다형성 polymorphism  
	- 여러가지 형태를 가질 수 있는 성질  
	- 상속관계에서 성립 가능한 문법  
	- 참조형의 업/다운 캐스팅 (부모타입의 변수에 자식객체로 new한 것이 들어갈 수 있다.)  

	1) instanceof 연산자  
		- 참조하는 변수가 가르키는 인스턴스(객체)의 실테 타입을 체크하는데 사용  
		- 참조변수 instaceof 클래스명 ---> true/false  
		- 클래스명 : 나부터 조상까지 모두 true로 리턴  
			     내아래 자식이나 아예다른 클래스는 False  

2. 추상 클래스  
	1) 개발할 때, 밑그림을 그리는 것. 설계 쪽에 해당  
	2) 키워드 : abstract  
	3) 추상메서드  
		abstract 키워드를 붙혀 메서드의 선언부만 있고, 구현부({코드작성...})은 되어있지 않은 상태  
    ```java
    public abstract String getName();
    public abstract void setName(String str);
    public abstract void getName() {} // 에러, 중괄호도 X
    ```
	
	4) 추상클래스  
		- 일반 멤버들과 함께 추상 메서드를 포함하는 클래스  
		- 추상 메서드가 하나라도 있으면 무조건 추상 클래스  
		- 클래스 앞에 abstract 붙이기  
		```java
		abstract class Shape {
			...
			abstract void draw(); //추상 메서드
		}
		```
	
	5) 추상클래스는 객체 생성 불가 **불완전한/ 미완성인 클래스**
		```java
		//Shape S = new Shape() // 에러
		Shape S; // 가능
		```	 
	6) 상속에서 수퍼클래스로 사용된다. 부모클래스가 될 수 있음. extends  
	7) 추상클래스를 물려받은 서브클래스를 완성시키려면, 추상메서드를 오버라이딩 해야한다.  
		 추상클래스를 단순히 상속만 받은 서브 클래스는 그 또한 추상클래스가 된다.  
		 구현을 하지 않는다면 클래스 앞에 abstract 키워드를 붙혀줘야한다.
		 * 추상클래스 단순 상속
		 	```java
			abstract class Rect extends Shape { // 추상draw();
				int width;
				...
			}
			new Rect(); // error
			```
		 * 추상클래스를 사용할 때 -> 상속받아 구현 완성
		 	```java
			class Rect extends Shape {
				int width;
				...
				@Override
				void draw() {
					// 추상메서드 구현코드 작성
				}
			}
			new Rect(); // 가능
			```
3. 인터페이스 interface : 규격  
	1) 키워드 :  interface  
		```java 
		interface PhoneInterface {
			// 내용 ...
		}
		```
	2) 인터페이스 멤버는 추상메서드와 상수만으로 구성  
		```java
		interface PhoneInterface {
			public static final 타입 상수명; // 상수
			public static final int BUTTONS = 20;
			public abstract 리턴타입 메서드명(); // 추상 메서드만
			public abstract void sendCall();
			public abstract void receiveCall();
		}
		```
	* 인터페이스 멤버는 누구나 접근가능하게 모두 public 접근지정자로 만듬  
	3) 모든 메서드는 public abstract이며, 생략 가능  
	4) 상수는 public static final이며, 생략가능하다.  
		```java
		interface PhoneInterface {
			int BUTTONS = 20; // 상수
			//추상 메서드
			void sendCall();
			void receiveCall();
		}
		```
	5) 인터페이스로 객체 생성 불가능.  
	    메서드는 모두 구현이 전혀 안된(미완성인) 추상체 메서드  
	6) 인터페이스로 레퍼런스 변수 선언은 가능  
		```java
		PhoneInterface iPhone; (O)
		new PhoneInterface(); (X)
		```
	7) 인터페이스 상속  
		인터페이스는 다른 인터페이스를 상속 받을 수 있다.  
		인터페이스는 규격과 같은 것으로, 상속을 통해 기존 인터페이스에 새로운 규격을 추가한 새로운 인터페이스를 만들 수 있다.  
		extends 키워드 사용  
		```java
		interface MobilePhoneInterface extends PhoneInterface { // + PhoneInterface의 변수와 메서드
			void sendSMS();
			void receiveSMS();
		}
		```
		- **인터페이스는 다중상속 허용**
		```java
		interface DualCameraPhoneInterface extends PhoneInterface, CameraInterface {
			// PhoneInterface와 CameraInterface의 변수와 메서드	
			void makeVideo();
		}
		```
	8) 인터페이스 구현 (사용하기)
		- 인터페이스의 추상메서드를 모두 구현한 클래스를 작성하는 것  
		- 키워드 : implements
		```java
		class IPhone implements MobilePhoneInterface { // 일반 클래스로만 구현 가능
			// 추상메서드 모두 구현
			public void sendCall(){ ... }
			public void receiveCall(){ ... }
			public void sendSMS() { ... }
			public void receiveSMS() { ... }
			// 추가적으로 일반 메서드나 변수 작성
			public int getButtons() { ... }
		}
		```  
		**추상 메서드중 하나라도 구현을 안하면 에러**  
	9) 인터페이스의 목적  
