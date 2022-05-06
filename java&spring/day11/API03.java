package day11;

public class API03 {
	public static void main(String[] args) {
		//String
		String a = new String("Hello");
		String b = new String("Hello");
		System.out.println(a == b); // 주소값 비교
		System.out.println(a.equals(b));// 값 비교
		
		// 리터럴 방식
		String str1 = "abc";
		String str2 = "abc";
		System.out.println(str1 == str2); // true -> 주소비교임. 실제로도 주소가 같다. 
		System.out.println(str1.equals(str2));
		
		System.out.println(System.identityHashCode("abc"));
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
	}
}
