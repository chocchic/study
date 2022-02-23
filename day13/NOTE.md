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
	3) 주요 메서드  
		- boolean mkdir() : 새로운 디렉토리(폴더) 생성
		- String[] list() : 디렉토리 내의 파일과 서브디렉토리 리스트를 배열로 리턴
		- File[] listFiles() : 해당 디렉토리 내의 파일과 서브디렉토리를 File배열로 리턴
		- String getPath() : 경로 문자열로 리턴
		- String getAbsolutePath() : 절대 경로를 문자열로 리턴
		- String getParent() : 파일이나 디렉토리의 부모디렉토리 이름 리턴
		- String getName() : 파일 또는 디렉토리의 이름 리턴
		- boolean isFile() : 파일이면 true
		- boolean isDirectory() : 디렉토리면 true
		- long length() : 파일 크기 리턴
		- boolean exists() : 파일 또는 디렉토리가 존재하면 true
