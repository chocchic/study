package day15;

import java.util.Arrays;
import java.util.List;

public class StreamEx05 {
	public static void main(String[] args) {

		List<String> names = Arrays.asList(
				"김피카츄", "박꼬북이",
				"김라이츄", "최파이리", 
				"김피카츄", "이상해씨");
		
		// 중복제거 
		names.stream()
		.distinct()
		.forEach(n -> System.out.println(n));
		
		System.out.println("--------------");
		
		// 필터링 
		names.stream()
		.filter(n -> n.startsWith("김"))
		.forEach(n -> System.out.println(n));

		System.out.println("--------------");
		
		// 중복제거 후 필터링 
		names.stream()
		.distinct()
		.filter(n -> n.startsWith("김"))
		.forEach(n -> System.out.println(n));
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
