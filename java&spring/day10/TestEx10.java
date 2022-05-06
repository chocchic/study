package day10;

class SpaceException extends Exception{
	public SpaceException(String msg) {
		super(msg); // Exception이 가진 (에러 메세지 받는 )부모 생성자 지정해서 호출
	}
}
class MemoryException extends Exception{
	public MemoryException(String msg) {
		super(msg);
	}
}

public class TestEx10 {
	public static void main(String[] args) {
		try {
			startInstall();
			copyFiles();
		}catch(SpaceException e) {
			System.out.println("애러 메세지 : " + e.getMessage());
			System.out.println("설치 공간을 확보한 후 다시 설치하시기 바랍니다.");
		}catch(MemoryException e) {
			System.out.println("에러 메세지 : " + e.getMessage());
			System.gc();
			System.out.println("다시 설치를 시도해주세요");
		}finally {
			deleteTempFiles(); // 설치가 잘되도, 안되면 다시 깔아야하니까 임시파일 삭제 해야하므로 finally
		}
	}
	static void startInstall() throws SpaceException, MemoryException {
		if(!enoughSpace())
			throw new SpaceException("설치 공간이 부족합니다.");
		if(!enoughMemory())
			throw new MemoryException("메모리가 부족합니다. 다른 프로그램을 종료해주세요.");
	}
	static void copyFiles() {
		
	}
	static void deleteTempFiles() {
		System.out.println("임시 파일 삭제... ");
	}
	static boolean enoughSpace() {
		return true;
	}
	static boolean enoughMemory() {
		return false;
		//return true;
	}
}
