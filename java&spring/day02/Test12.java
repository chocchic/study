package day02;

public class Test12 {

	public static void main(String[] args) {
		// 사칙연산 : + - * / %
		int a = 10 + 10;
		System.out.println(a); // 20
		
		int b = 10 %3;
		System.out.println(b);
		
		int c = 10/3;
		System.out.println(c);
		
		//overflow
		/*
		long d = 100000*100000;
		//long 해도 깨짐 왜?? int *int이므로
		*/
		long d = 100000 * 100000L;
		System.out.println(d); 		
		
	}

}
