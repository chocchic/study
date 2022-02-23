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
		바이트 스트림을 문자 스트림으로 연결해주는 역할을 하는 보조 클래스  
		바이트 스트림의 데이터를 지정도니 인코딩의 문자 데이터로 변환하는 작업 수행  
		
	2) OutputStreamWriter  
		파일에 텍스트 데이터를 저장할 때 원하는 인코딩으로 지정하며 저장  
		인코딩 지정안하면 OS에서 사용하는 인코딩으로 데이터 저장(eclipse에 지정한 인코딩)  
5. OS
	하나의 소프트웨어로, 소프트웨어들을 관리하는 소프트웨어  
	Windows, macOS, linux, android, iOS  
	자바 -> JVM  
6. Thread 스레드  
	1) 프로세스 process  
		흔히 말하는 프로그램  
		프로그램을 수행하는데 필요한 데이터와 메모리등 자원 + 스레드  
		싱글 프로세스 : 하나의 프로그램을 돌리기 위해 1개의 프로세스가 실행되는 것  
		멀티 프로세스 : 하나의 프로그램을 돌리기 위해 여러개의 프로세스가 실행되는 것  
	2) 멀티 태스킹  
		태스크 task : 프로세스가 하는 일  
		멀티 태스킹 : 하나의 프로세스가 여러 일을 하는 것  
	3) 스레드 Thread  
		하나의 테스크를 수행하는 아이  
		프로세스 = 공장(자원 + 일꾼), 스레드 = 일꾼  
		싱글 스레드 = 자원 + 일꾼  
		멀티 스레드 = 자원 + 일꾼 + 일꾼 ...  
	4) 멀티태스킹과 멀티스레딩
		멀티 태스킹을 실현하기 위한 2가지 방법  
		- 멀티 프로세싱  
		- 멀티 스레딩  
			웹 서버, 워드  
	5) 자바의 멀티스레딩  

7. 스레드 만들기  
	- 개발자가 할일  
		1. 스레드가 할 일 코드 작성  
		2. 스레드 생성해서 JVM에게 일하도록 요청  
	- 구현 방법  
		1. Thread 클래스 상속  
		2. Runnable 인터페이스 구현  
		
		1) Thread 클래스 상속받아 스레드 만들기  
			1. 주요 메서드 
				- void run() : JVM에 의해 호출 ( 핵심 메서드 ), 오버라이딩 하여 스레드가 실행항 코드 작성  
				- void start() : JVM에게 스레드 실행하도록 요청  
				- void interrupt() : 스레드 강제 종료  
				- static void yield() : 다른 스레드에게 실행 양보.
				- String getName() : 스레드 이름 리턴
				- int getPriority() : 스레드 우선순위값 리턴 1~10
				- void setPriority(int n) : 우선순위 n으로 지정
				- Thread.State getState() : 스레드의 상태값 리턴
				- static void sleep(long millis) : mills 시간동안 스레드 일시정지
				- static Thread currentThread() : 현재 실행중인 스레드 객체 리턴  

		2) Runnable 인터페이스로 스레드 만들기  
			Runnable 인터페이스는 run() 메서드 하나만 가지고 있다.  
		3) 스레드 상태  
			- NEW  : new Thread()  
			- RUNNABLE  : start()  
			- TIMED_WAITING  : sleep(1000)  
			- BLOCK  : i/o 작업  
			- WAITING  : wait(), notify()로 깨울 때까지 기다림  
			- TERMINATED   : 종료된 것  
		4) 스레드 종료시키는 방법  
			1. run() 종료  
			2. interrupt() 호출  
		5) 스레드 동기화 Synchronization  
			- 멀티 스레드에서 공유 자원 혹은 공유 데이터에 동시 접근할 때 문제가 발생할 수 있다.  
			- 스레드 동기화 방법  
				- synchronized 로 동기화 블럭 지정  
				- wait() - notify() 메서드로 스레드 실행 순서 제어  
			1. 메서드 전체를 임계 영역으로 지정
				```java
				synchronized void add() {
					...
				}
				```
			2. 코드 블럭을 임계영역으로 지정
				```java
				void execute(){
					...
					synchronized(this) {
						...
					}
					...

8. Network
	1) 네트워크 : 데이터 전송을 할 수 있는 통신망, 전자장비들의 집합  
		노드 Node : 네트워크에 연결된 장치  
		호스트 Host : 노드에게 하나이상의 서비스를 제공해주는 노드  
	2) OSI 7계층 : 모든 네트워크 통신에서 생기는 여러가지 충돌 문제를 완화하기 위해, 국제표준기구(IOS)에서 표준화된 네트워크 구조를 제시한 기본 모델  
		7 응용층
		6 표현층
		5 세션층
		4 전송층
		3 네트워크층
		2 데이터링크층
		1 물리층
		- 라우터와 스위치  
	3) 네트워크 유형  
		LAN : Local Area Network  
			나와 같은 네트워크 간의 통신 -> 스위치  
		WAN : Wide Area Network  
			내부 -> 외부			
