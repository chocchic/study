package day14;

import java.util.function.ToIntBiFunction;

public class RefEx02 {
	
	public static void print(int order) {
		if(order < 0) System.out.println("앞에가 사전순으로 먼저 나온다.");
		else if(order == 0) System.out.println("동일한 문자열이다.");
		else System.out.println("앞에가 사전순으로 나중에 나온다.");
	}
	
	public static void main(String[] args) {

		// 객체 두개 던지면 int로 리턴해주는 
		// int applyAsInt(T t, U u) 
		ToIntBiFunction<String, String> function; 
		
		// 일반 람다 버전 
		function = (a, b) -> a.compareTo(b); 
		print(function.applyAsInt("java", "JAVA"));
		
		// 메서드 참조 버전 
		function = String::compareToIgnoreCase;
		print(function.applyAsInt("spring", "Spring"));
		
		
		
		
		
		
	}
}
