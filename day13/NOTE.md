0. 입출력 : java.io  
	io == I/O == Input/Output  
	input ( 사용자가 upload -> 자바 안으로 가져온다)  
	output ( 자바에서 내보내기 -> download )  
 
1. File 클래스 : java.io.File  
	크기, 생성, 삭제, 변경 등등 파일에 관련된 내용을 관리하는 기능.  
	입출력 기능 X.
	1) 생성
		- File(String pathname) : pathname 파일 전체 경로명을 작성하여 객체 생성
		- File(String parent, String child) : parnet 디렉토리에 child이름으로 서브디렉토리나 파일을 작성하며 객체 생성
	2) 객체 생성
		```java
		File 변수명 = new File("전체 경로");
		File 변수명 = new File("디렉토리 경로", "파일명");
		```
		**경로나 파일명 작성시 확장자명까지 포함해서 작성!!**	
