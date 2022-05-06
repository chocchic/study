package day01;

public class Test06 {

	public static void main(String[] args) {
		
		// 강제 형변환
		int a = 65;
		char b = (char)a;
		System.out.println(b);
		
		// 자동 형변환
		char c = 'A';
		int d = c;
		System.out.println(d);
		
		// 자동 형변환
		int e = 5;
		double dd = e; //dd=8byte, e=4byte
		System.out.println(dd);
		
		// 강제 형변환
		double f = 5.8;
		int h = (int)f;//double = 8byte, int = 4byte
		System.out.println(h);
		
		// 자동 형변환
		char cc = 'a';
		double jj = cc; // cc= 1byte, jj= 8byte
		System.out.println(jj);
		
		
	}
}
