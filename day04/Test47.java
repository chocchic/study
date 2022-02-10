package day04;

public class Test47 {
	public static void main(String[] args) {
		// 랜덤 : 난수(임의의 수)
		// java.lang.math : 임포트 필요 없이 바로 사용 사능
		System.out.println(Math.random()); // 0~1사이 double값 나옴
		
		// 0~9 값
//		for(int i = 0; i< 10; i++) {
//			System.out.println((int)(Math.random()*10));
//		}
		
		// 문제 1. 1 ~10 사이 랜덤값 구하기
		System.out.println((int)(Math.random()*10)+1);
		// 문제 1-1. 0~8
		System.out.println((int)(Math.random()*9));
		// 문제 2. 13~28사이 랜덤값 구하기
		//0~15까지 랜덤 +13
		for(int i = 0; i <20;i++) {
		System.out.println((int)(Math.random()*16)+13);
		}
	}
}
