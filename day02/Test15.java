package day02;

public class Test15 {
	public static void main(String[] args) {
		//대입연산자
		int a = 10;
//		System.out.println(a + 10); //20
//		System.out.println(a);//10
//		
//		System.out.println(a+=10);//20 <-복합연산자 a에 저장됨!!
//		System.out.println(a);//20
		
		// 복합 대입연산자 : 산술 + 대입: += -= *= /= %=
		a = a+1;
		System.out.println(a);
		a +=1;
		System.out.println(a);
		a *= 10;
		System.out.println(a);
		
	}
}
