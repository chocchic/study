package day01;

public class Test02 {
	public static void main(String[] args) {
		//변수 선언
		boolean b;
		char ch;
		int i;
		double d;
		b=true;
		ch = 'A';
		i = 100;
		d = 1.23;
		
		int a,c;	
		
		System.out.println(b);
		System.out.println(ch);
		System.out.println(i);
		System.out.println(d);
		
		int abc = 10;
		int def = 10, ghi = 20;
		System.out.println(abc);
		System.out.println(def);
		System.out.println(ghi);
		abc= 200;
		System.out.println(abc); //마지막에 대입한 값만 남아있음을 확인
		
		//대입 연산자
		abc = 10+20;
		System.out.println(abc);
		
			
	}
}
