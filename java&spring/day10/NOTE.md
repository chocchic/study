1. 싱글턴 Singleton (싱글인스턴스)  
	: 객체 단 하나만 생성된다 하여 싱글턴이라함.  

	1) 구성요소  
	
	  - 1 자기 클래스 타입의 private static 변수  
	      : 내클래스안에 한번만 객체생성하여 로딩되게 미리 만들어놓고 값변경도 외부에서 못하게 private으로 처리  
        ```java
        private static 클래스명 instance = new 클래스명(); 
        ```
    - 2 private 생성자 : 외부에서 객체생성 못하게 막기  
	    ```java
      private 클래스명() {} 
      ```
    - 3 외부에서 instance를 가져다 쓸수 있도록 get메서드 public으로 생성  
		    외부에서 객체생성이 안되므로, 메서드도 static 붙힘  
        ```java
        public static 클래스명 getInstance() {
      		return instance; 
	      }
        ```

2. 내부클래스 inner class, 중첩클래스, nested class  
	클래스 내부에 또 다른 클래스를 정의한 것  
	이유 : 은닉화, 보안강화, 코드 간결, 멤버 쉽게 접근  

	1) 구조  
      ```java
      class Outterclass {
        외부클래스 변수...
        외부클래스 메서드...
        class Innerclass {
          내부 변수..
          내부 메서드... 
        }
      }
      ```

	2) 종류 : 클래스 정의(선언) 위치에 따라 구분  
		
		1. 멤버 클래스  
			- 1 인스턴스 멤버 클래스 : A클래스 객체 생성해야만 사용할 수 있는 B내부 클래스  
          ```java
          class A {
            ..
            class B {
              ..
            }
          }
          ```
			- 2 static 멤버 클래스 : A클래스를 통해 바로 접근 가능한 B 내부클래스  
          ```java
          class A {
            ..
            static class B {
              ..
            }
          }
          ```  
          
        * 멤버클래스도 하나의 클래스이므로 컴파일하면 바이트코드파일(.class) 별도로 생성  
            A$B.class

		2. 로컬 클래스 : method()가 실행될때만 사용할 수 있는 B 내부클래스.  
			```java
			class A {
				void method() {
					class B {
						....
					}
				}
			}
      ```
      
			* A$1B.class생성

	3) 인스턴스 멤버 클래스 : 인스턴스 변수와 메서드만 선언가능, static 변수와 메서드 선언 불가  
		- 정의 
		  ```java
      class A {
        class B {
          B(){}
          int x; 
          void method() {..}
          //static int y; 		(X)
          //static void method2() {..}	(X)
        }
      }
      ```  
      
		- 객체 생성 : A외부에서 B생성 -> 먼저 A로 객체생성하고, B 객체 생성   
      ```java
      A a = new A(); 
      A.B b = a.new B(); 
      b.x = 10; 
      b.method(); 
      ```
	4) static 멤버 클래스 : static 키워드가 붙은 내부 클래스. 모든 종류의 변수와 메서드 선언 가능.  
	  - 정의 
      ```java
      class A {
        static class B {
          B(){}
          int x; 
          void method() {..}
          static int y; 		
          static void method2() {..}	
        }
      }  
		  //- 객체 생성 : A외부에서 B생성 -> A객체 생성 필요없음.  
    	A.B b = new A.B(); 
		  b.x = 10;  // 인스턴스 멤버 
		  b.method(); 
		  A.B.y = 20; // 클래스 멤버 
		  A.B.method2(); 
      ```
      
	5) 로컬(지역) 클래스 : 접근제어자와 static을 붙힐 수 없다. 메서드 실행할때 메서드 내부에서만 사용되므로 제한할 필요가 없다. 인스턴스변수와 메서드만 선언 가능.  
		- 정의  
      ```java
      void methodA() {
        class B {
          B(){}
          int x; 
          void method() {..}
          //static int y; 		(X)
          //static void method2() {..}	(X)
        }
       // 객체 생성 
        B b = new B(); 
        b.x = 100; 
        b.method(); 
      }
      ```
      
3. 익명 클래스 anonymous class : 이름이 없는 클래스  
	- 객체 이름이 없지만 클래스를 정의하고 동시에 객체를 생성함.  
	- 변수의 초기값, 지역변수의 초기값, 매개변수의 매개값으로 주로 대입됨.  
	- 단독으로 생성 불가, 클래스를 상속하거나 인터페이스를 구현해야 생성가능.  

	1) 구조 
	  ```java
		new 클래스명() {
			// 클래스 정의...
		}

		new 인터페이스명() {
			// 메서드 오버라이딩... 
		}
    ```
4. 예외 처리 Exception handling : 코드로 발생할 수 있는 에러를 미리 진단하고, 해결방안을 코드로 처리해 놓은것.  
	- 컴파일 에러 : 컴파일 할때발생 (실행조차X) : 문법 오류  
	- 런타임 에러 : 실행하다 발생 : 문법적으로 맞아 컴파일을 되는데 입력오류 등으로 실행도중 에러  

	1) 예외(Exception) 에러(Error)  
		- 예외 : 프로그램 코드로 해결 가능한 것들  
		- 에러 : 문법적으로 해결 못하는 것들  
	2) 방법 : try-catch 구문  
	  ```java
		try {
			// 예외가 발생할 것 같은 코드들... 	
		}catch(ArrayIndexOutOfBoundsException e) {
			// 예외가 발생하면 대처할 코드들... 
		}catch(Exception e) {
			// 예외가 발생하면 대처할 코드들... 
		}
    ```
	3) 대표적인 예외  
		- ArrayIndexOutOfBoundsException : 배열 인덱스범위 틀림  
		- ClassNotFoundException : 찾는 클래스가 없음  
		- ArithimeticException : 정수를 0으로 나누려할 때  
		- ClassCastException : 변환할 수 없는 타입으로 객체를 형변환할 때  
		- NullPointerException : 생성 안하고 쓰려고 할 때  
		- IllegalArgumentException : 잘못된 인자 전달시 발생  
		- NumberFormatException : 문자-> 숫자 형태가 안맞아 발생  
		- InterruptedException : thread가 멈취있다 다시 실행안하거나, 일시정지인데 완전히 꺼지거나 하는 경우  
		- IOException : 입출력 동작 실패 또는 인터럽트시 발생  
		- OutOfMemoryError : 메모리 부족시 발생  
	
	4) finally  
		예외 여부와 상관없이 무조건 실행되는 블럭  
      ```java
      try{
        예외 발생할 것 같은 코드들...
      }catch(Exception e) {
        예외 발생시 처리해줄 코드...
      }finally {
        예외 발생 여부와 상관없이 무조건 실행되는 문장 
      }
      ```
	
	5) 예외 발생 시키기
		고의로 예외 발생시키는 것
		```java
		Exception e = new Exception();
		throw e;
    ```
    
	6) 상속 구조  
		Object > Throwable > Exception  
				               > Error  
