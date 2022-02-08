package day02;

public class Test11 {

	public static void main(String[] args) {
		// 문제1. 결과를 먼저 주석으로 작성해보고 실행해서 확인
		int i = 5, j = 5;
		System.out.println(i++); // i = 5
		System.out.println(++j); // j = 6
		System.out.println("i=" + i + ", j=" + j); // i= 6, j=6
		
		//문제 2.
		int k = 10;
		System.out.println(k++); // 10
		System.out.println(++k); // 12
		++k; //k = 13 출력은 X
		System.out.println(++k); // 14
		System.out.println(k++); // 14
		System.out.println(k); // 15
		
		//문제 3.
		int a = 10, b = 10;
		int r = ++a + ++b; // r = 11+10 = 21 (a=11, b=11)
		r = ++a + b++; // r = 12 + 11 = 23 (a=12,b=12)
		r = ++a+ a; // r = 13 + 12 = 25 (a=13,b=12)
		r = ++a + a ++; // r = 14 + 14 = 28 (a=15,b=12) 
		r = ++a + ++a; // r= 16+17 = 33 (a=17,b=12)
		System.out.println(r); // 33
	}

}
