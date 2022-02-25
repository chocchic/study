package day15;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx03 {
	
	public static int sum = 0; 
	
	public static void main(String[] args) {

		String[] strArr = { "aaa", "bbb", "ccc" }; 
		// 배열은 stream() 메서드가 존재하지 않아서 
		// Arrays.stream(타입[]) 클래스메서드를 이용하여 
		// stream을 얻어올 수 있다. 
		Stream<String> strStream = Arrays.stream(strArr); 
		strStream.forEach(a -> System.out.println(a));
		
		int [] intArr = {1,2,3,4,5}; 
		IntStream intStream = Arrays.stream(intArr); 
		intStream.forEach(a -> System.out.println(a));
		
		
		// 숫자 범위로 스트림 얻기  // rangeClosed 1 ~ 100 
		IntStream stream = IntStream.rangeClosed(1, 100);  // range 1 ~ 99 : 4950
		stream.forEach(a -> sum += a);
		System.out.println("총합 : " + sum);
		
		
		
		
		
		
		
		
		
	}
}
