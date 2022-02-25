package day15;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StreamEx08 {
	public static void main(String[] args) {
		
		// 숫자 
		IntStream intStream = Arrays.stream(new int[] {5,3,2,4,1}); 
		intStream
		.sorted()
		.forEach(n -> System.out.println(n));
		System.out.println();
		
		// 객체 요소일 경우 
		List<Student> stdList = Arrays.asList(
				new Student("ccc", 20),
				new Student("bbb", 10),
				new Student("aaa", 30)
		);
		
		stdList.stream()
		.sorted()
		.forEach(s -> System.out.println(s.getScore() + " " + s.getName()));
		
		System.out.println();
		
		
		stdList.stream()
		.sorted(Comparator.reverseOrder())
		.forEach(s -> System.out.println(s.getScore() + " " + s.getName()));
		
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
