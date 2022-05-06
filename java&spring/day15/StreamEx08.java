package day15;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StreamEx08 {
	public static void main(String[] args) {
		// 숫자
		IntStream intStream = Arrays.stream(new int[] {5,3,2,4,1});
		intStream.sorted().forEach(n -> System.out.print(n + " ")); // default : 오름차순 정렬
		//intStream.sorted(Comparator.reverseOrder()).forEach(n -> System.out.println(n)); // 인자 추가시 내림차순 정렬 가능, 배열은 이 방법 X
		System.out.println();
		
		//
		List<Student> stdList = Arrays.asList(new Student("aaa",20),new Student("bbb",10),new Student("ccc",20),new Student("ddd",40));
		stdList.stream().sorted().forEach(s -> System.out.println(s.getScore() + " " + s.getName()));
		
		System.out.println("reverse");
		stdList.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.println(s.getScore() + " " + s.getName()));
	}
}
