package day15;

import java.util.Arrays;
import java.util.List;

public class StreamEx13 {
	public static void main(String[] args) {
		List<Student> stdList = Arrays.asList(new Student("aaa" ,10), new Student("bbb",20), new Student("ccc", 30), new Student("ddd",40));
		
		int sum = stdList.stream().mapToInt(Student::getScore).sum();
		
		int sum2 = stdList.stream().map(Student::getScore).reduce((a, b)->a+b).get();
		
		int sum3 = stdList.stream().mapToInt(Student::getScore).reduce(0, (a, b)->a+b); // 요소가 하나도 없으면 0 취급해라
		
		System.out.println("1 : " + sum + " 2 : " + sum2 + " 3 : " + sum3);
	}
}
