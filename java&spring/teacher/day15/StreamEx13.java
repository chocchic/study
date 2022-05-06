package day15;

import java.util.Arrays;
import java.util.List;

public class StreamEx13 {
	public static void main(String[] args) {

		List<Student> stdList = Arrays.asList(
				new Student("aaaa", 10),
				new Student("bbbb", 20),
				new Student("cccc", 30),
				new Student("dddd", 40)
		);
		
		int sum = stdList.stream()
				.mapToInt(Student::getScore)
				.sum();
		
		int sum2 = stdList.stream()
				.map(Student::getScore)
				.reduce((a, b) -> a + b)
				.get();
		
		int sum3 = stdList.stream()
				.mapToInt(Student::getScore)
				.reduce(0, (a,b)-> a+b); 
		
		System.out.println("sum1 : " + sum);
		System.out.println("sum2 : " + sum2);
		System.out.println("sum3 : " + sum3);
		
		
		
		
		
		
		
		
		
		
	}
}
