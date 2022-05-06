package day03;

public class Test24 {
	public static void main(String[] args) {
		for(int i = 0; i<5;i++) {
			System.out.println("hi");
		}
		//System.out.println(i); //error
		//for 문 내에서 만들어진 변수는 밖에서는 사용 불가
		for(int i = 0; i<10;i++) { // 그래서 재선언해도 오류 아님
			System.out.println("hi");
		}
	}
}
