package day14;

import java.util.function.ToIntBiFunction;

public class RefEx02 {
	
	public static void print(int order) {
		if(order < 0) System.out.println("앞에가 사전순으로 먼저 나온다.");
		else if(order == 0) System.out.println("동일한 문자열이다.");
		else System.out.println("앞에가 사전순으로 나중에 나온다");
	}
	public static void main(String[] args) {
		
		// 객체 2개 던지면 int로 리턴
		// int applyAsInt(T t,U u)
		ToIntBiFunction<String, String> function;
		
		function  = (a,b)->a.compareTo(b); // 사전순 비교,  동일하면 0 앞에게 크면 음수, 뒤에게 크면 양수
		System.out.println(function.applyAsInt("java","JAVA"));
		
//		function = String::compareTo;
		function = String::compareToIgnoreCase; // 대소문자 무시하고 비교
		print(function.applyAsInt("spring", "Spring"));
	}
}
