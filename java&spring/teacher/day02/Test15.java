package day02;

public class Test15 {
	public static void main(String[] args) {

		// 대입연산자 : 대입을 해야만 변수의 값이 변경된다. 
		int a = 10; 
//		System.out.println(a + 10);
//		System.out.println(a); // 10 
//		
//		System.out.println(a += 10);
//		System.out.println(a);
		
		// 복합 대입연산자 : 산술+대입 : += -= *= /= %=
		a = a + 1; 
		System.out.println(a);
		a += 1;
		a++;
		a--;
		System.out.println(a);
		a -= 1; 
		System.out.println(a);
		a *= 10; 
		System.out.println(a);
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
