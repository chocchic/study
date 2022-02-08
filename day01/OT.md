# javajavajava : 블록체인기반 핀테크 서비스 개발자 양성

1. 스케줄  
***
	자바 				   3주
	html,css,javascript,sql		    2주
	jsp X, 프레임워크(spring)		3주
	팀 프로젝트				3주
	4월29일 프로젝트 발표
	
2. 개발환경 설정  
***
	0) 구글 크롬 브라우저 > 다운, 설치(기본브라우저설정)  
	
	1) jdk11 설치  
		구글 > jdk 검색 > [오라클 다운로드](https://www.oracle.com/java/technologies/downloads/) >jdk11 다운  
		설치(계속 next)  
		환경변수 설정  
			내PC(우클릭) or 윈도우버튼(우클릭) > 설정 > 고급시스템 설정 >시스템 변수 밑 새로만들기  
			변수명 : JAVA_HOME   
			변수 값: C:\Program Files\Java\jdk-11.0.14 (내 PC에 맞게 확인하기)  
			시스템 변수에 있는 Path (더블클릭) > 새로만들기 > %JAVA_HOME%\bin (오류나면 새로만든거 ~javapath 아래까지 위로 올리기함)  
		cmd 켜서 java -version, java, javac 쳐서 자바 설정 확인 < jdk 설치가 되었는지 확인  
	
	2) 이클립스 eclipse  
		[이클립스 패키지 다운로드](https://www.eclipse.org/downloads/packages) > EE버전으로 다운(윈도우용인지 확인) > 작업 폴더에 압축해제  
		실행 > browse로 workspace 미리 만들어놓은 곳으로 변경  
		오른편 위 + 표시 아이콘 클릭해서 java환경으로 바꾸기(open perspective -> java로 선택, 확인)  
		window -> preferences -> encoding 검색 -> content types빼고 다 encoding을 UTF-8로 변경(원래는 Korean~뭘로 되어있을 것)  
		* 프로젝트 만들때 실행환경 JRE가 JAVASE-11로 되어있는지 확인  
	
	3) 폴더명/파일명 : 한글, 특수문자, 띄어쓰기 X  
		  내 문서, 바탕화면 X -> PC의 root(C드라이브 또는 D드라이브)  
	   	  클래스 이름은 항상 대문자  

3. 자바란  
***
	Test01.java -> 컴파일(컴파일러) -> Test01.class -> JVM(Java Virtual Machine)이 실행  
	절차지향 언어 : c 언어  
	객체지향 언어 : java, C++  
	1) 특징  
		플랫폼 독립성  
		객체 지향  
		멀티스레드  
		가비지컬렉션  	
	2) JVM Java Virtual Machine 자바 가상 기계  
	3) 자바 플랫폼  
		JDK Java Development Kit : 자바 응용프로그램 개발, 실행환경  
		JRE Java Runtime Environment : 자바 응용프로그램이 실행될 때 필요한 SW를 가지고 있다.  
	4) Java API  
		[java api docs](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
