package day03;

public class Test25 {
	public static void main(String[] args) {
		
		for(int a = 0; a < 10; a++) {
			System.out.println("hi");
		}
		int a = 0;
		for(;a<10; a++) { // 초기식 생략 가능
			System.out.println("hello");
		}
		
//		for(;;) { // 무한 반복
//			System.out.println("무한반복");
//		}
		
		for(int i = 0; i < 10; i++) {
			if(i==5) {
				continue; // 조건이 참일 떄 바로 증감문으로 넘어감
			}
			System.out.println(i);
		}
	}
}
