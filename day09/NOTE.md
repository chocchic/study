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
	
	5) 추상클래스는 객체 생성 불가.
		**불완전한/ 미완성인 클래스**
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
