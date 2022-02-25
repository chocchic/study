package day15;

import java.util.Arrays;

public class StreamEx09 {
	public static void main(String[] args) {

		int [] arr = {1,2,3,4,5}; 
		int total = Arrays.stream(arr)
				.filter(a -> a % 2 == 0)
				.peek(n -> System.out.println(n))
				.sum(); 
		System.out.println("total :" + total);
		
		Arrays.stream(arr)
		.filter(a -> a % 2 == 0)
		.forEach(n -> System.out.println(n)); 
		
		
		
		
		
		
		
		
		
		
	}
}
