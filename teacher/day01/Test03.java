package day01;

public class Test03 {
	public static void main(String[] args) {

		char ch = '\u0041'; 
		System.out.println(ch);
	
		// 오버플로우 
		byte bb = (byte)130;
		System.out.println(bb);
		
		// int : 10진수, 8진수:0으로시작하는수, 16진수:0x로 시작, 
		//		2진수 
		int i = 15; // 10진수
		int j = 015;
		int k = 0x15; 
		int l = 0b0101;
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(l);
		
		// 문자열 
		String str = "안녕하세요";
		System.out.println(str);
		str = "피카츄";
		System.out.println(str);
		str = "A";
		
		float f = 3.14F;
		double d = f;
		long ll = 100L; 
		int iii = (int)ll;
		
		
		
		
		
	}
}
