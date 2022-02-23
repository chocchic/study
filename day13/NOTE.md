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
		- boolean createNewFile() : 빈 파일 생성
		- boolean delete() : 파일 또는 디렉토리 삭제
		- boolean renameTo(File dest) : dest 경로명으로 파일 이름 변경
		- long lastModified() : 마지막으로 변경 시간 리턴
		
		- **경로**  
			- 절대 경로 : c 또는 d드라이브부터 시작하는 전체 경로  
			- 상대 경로 : 내 현재 파일을 기준으로 말하는 경로  
				- ./ : 현재 폴더  
				- .. : 상위 폴더  
				- / : 안에  
				- 예시
					c:\java\abc\test.txt
					c:\java\abc\test2.txt
					c:\java\def\hello.txt
					c:\java\hahaha.txt
					![filestructure](./file.jpg)
2. Stream 스트림  
	연속적인 데이터의 흐름 -> 데이터가 돌아다니는 통로(선) -> 데이터를 목적지로 i/o하기 위한 방법.
	- 스트림은 단방향
	- 자바 응용프로그램은 입력스트림과 출력 스트림과만 연결하고, 입출력장치 제어하고 실질적인 입출력을 담당하는 것은 입출력 스트림이다.
	
	- 입력장치 or 파일 ----> 입력 스트림 ---->  자바 응용프로그램
	- 입력장치 or 파일 <---- 출력 스트림 <----  자바 응용프로그램
	
	- 스트림을 통해 흘러가는 기본 단위는 바이트나 문자이다.  
	- 자바스트림 객체는 바이트 단위로 입출력하는 바이트 스트림과 문자 단위로 입출력하는 문자 스트림으로 나뉜다.  
	- 바이트 스트림을 다루는 클래스는 공통적으로 뒤에 Stream이 붙고 문자 스트림을 다루는 클래스는 뒤에 Reader/Writer를 붙여 구분한다.
	
	- FIFO First In First Out : 선입선출
	- LIFO Last In First Out : 후입선출

	- close() : 스트림을 열었으면 사용 후 반드시 닫아줘야 누수가 안된다.

3. 바이트 스트림
	- 바이트 단위로 데이터 전송  
	- 다양한 클래스 제공으로 용도에 맞게 골라 사용  
	- 이미지, 동영상 전송 가능   

	|InputStream|OutputStream|
	|--------------|----------------|
	|int read()| void write(int b)|
	|int read(byte[] b)| void write(byte[]b)

	1) FileOutputStream
		파일에 출력을 하기 위한 클래스.  
		파일에 바이너리 데이터 저장  

		1. 생성자  
			FileOutputStream(File file) : 파일 객체에 출력 / 저장  
			FileOutputStream(File file, boolean append) : 파일 객체에 파일의 마지막부터 데이터 출력 / 저장  
		2. 주요 메서드
			- void write(int b) : 출력  
			- void wirte(byte[] b)  
			- void flush() : 출력스트림에 남아있는 바이너리 데이터 모두 출력  
			- void close() : 출력스트림 닫고 관련된 시스템 자원 해제  
	2) FileInputStream  
		바이트 스트림으로 파일 읽는 스트림 클래스  
	
		1. 생성자(API 문서 참조)  
		2. 주요 메서드  
			- int read() : 한 바이트씩 읽어서 int로 리턴  
			- int read(byte[] b) : b의 크기만큼 바이트 읽음. 읽는 도중 EOF를 만나면 실제 읽은 바이트 수 리턴  
			- int read(byte[] b, int off, int len) : off부터 시작해서 len만큼 읽기  
			- void close()  

			**EOF** : End Of File 파일의 끝. read()할 때 EOF를 만나면 -1를 리턴한다.  
			byte 0xFF -> int 0x000000FF  
			EOF -1 int -> 0xFFFFFFFF  

4. 문자 스트림  
	- 2 바이트의 유니코드 문자를 단위로 입출력하는 스트림  
	- 문자화 되지 않는 바이너리 바이트 값들을 처리 불가 -> 이미지같은 것 처리 불가  
	
	1) InputStreamReader  
	
	2) OutputStreamWriter
