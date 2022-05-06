package day15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx02 {
	public static void main(String[] args) {

		List<Student> list = Arrays.asList(
				new Student("aaa", 90),
				new Student("bbb", 93),
				new Student("ccc", 95) 
				);
		
		double avg = list.stream() 	 // 스트림 가져옴 
				.mapToInt(Student :: getScore) // 중간처리 : 학생 객체를 점수로 매핑 
				.average()		// 최종처리 : 평균 구하기 
				.getAsDouble(); // 최종결과 리턴받기 
		
		System.out.println("평균 점수 : " + avg);
	
		
		/*
		Stream<Student> stream = list.stream(); 
		stream.forEach(s -> {
			String name = s.getName();
			int score = s.getScore(); 
			System.out.println(name + " : " + score);
		});
		*/
		
		
		
		
		
		
		
		
		
		
		
	}
}
