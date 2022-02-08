package day02;

public class Test10 {

	public static void main(String[] args) {
		// 증감 연산자 : ++ --
		// 전위형 : 나먼저 연산
		// 후위형 : 가장 나중에 연산
		// 단독으로 사용되면 전/후 상관없다.
		int a = 10;
		/*
		 * System.out.println(a); ++a;//a값에 1이 더해짐 System.out.println(a); a++;
		 * System.out.println(a); --a; //a값에 1빼기 System.out.println(a); a--;
		 * System.out.println(a);
		 */
		
		System.out.println(++a); // a= 11
		System.out.println(a++); //a = 11, 이후에 a출력하면 12, 후위형이기 때문에 출력후에 +1
		System.out.println(a);
	}

}
