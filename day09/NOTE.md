1. 다형성 polymorphism  
	여러가지 형태를 가질 수 있는 성질  
	상속관계에서 성립 가능한 문법  
	참조형의 업/다운 캐스팅  
	(부모타입의 변수에 자식객체로 new한 것이 들어갈 수 있다.)  

	1) instanceof 연산자  
		참조하는 변수가 가르키는 인스턴스(객체)의 실테 타입을 체크하는데 사용  
		참조변수 instaceof 클래스명 ---> true/false  
		클래스명 : 나부터 조상까지 모두 true로 리턴  
			내아래 자식이나 아예다른 클래스는 False  

2. 추상 클래스  
	1) 개발할 때, 밑그림을 그리는 것. 설계 쪽에 해당  
	2) 키워드 : abstract  
	3) 추상메서드 : abstract 키워드를 붙혀 메서드의 선언부만 있고, 구현부({코드작성...})은 되어있지 않은 상태  
    	```java
    	public abstract String getName();
	public abstract void setName(String str);
	//public abstract void getName() {} // 에러, 중괄호도 X
    	```
	4) 추상클래스  
		
