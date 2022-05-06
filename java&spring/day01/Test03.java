package day01;

public class Test03 {

	public static void main(String[] args) {
		
		char ch = '\u0041'; // unicode로 A
		System.out.println(ch);
		
		byte bb = 127; // 128부터 에러
		//오버플로우
		byte b = (byte)130;
		System.out.println(bb);
		System.out.println(b);
		
		//int : 10진수, 8진수 : 0으로 시작하는 수, 16진수, 2진수
		int i = 15;  // 10진수
		int j = 015; // 8진수
		int k = 0x15;// 16진수 자바스크립트에서 자주 사용하게 되는데 #fff 이런 식으로 표현할 것.
		int l = 0b0101;//2진수
		System.out.println(i);
		System.out.println(j);// 13 015는 8진수로 인식. 그 8진수에 맞는 10진수로 변환하여 출력
		System.out.println(k);// 21
		System.out.println(l);// 5
		
		// 문자열
		String str = "안녕하세요";
		System.out.println(str);
		str = "피카츄";
		System.out.println(str);
		str = "A";
		
		float f = 3.14F; // 3.14만 넣으면 오류
		double d = f;
		long ll = 100L;
		long il = 1231;
		
	}

}
