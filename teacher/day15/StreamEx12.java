package day15;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class StreamEx12 {
	public static void main(String[] args) {
		
		// 요소가 없을때 예외발생 예시 : java.util.NoSuchElementException
		List<Integer> list = new ArrayList<Integer>(); 
		/*
		double avg = list.stream()
			.mapToInt(Integer::intValue)
			.average()
			.getAsDouble(); // 여기서 에러 발생 
		*/
		
		// 해결방안 #1. 
		OptionalDouble opt = list.stream()
			.mapToInt(Integer::intValue)
			.average(); 
		
		if(opt.isPresent())
			System.out.println("평균1 : " + opt.getAsDouble());
		else 
			System.out.println("평균1 : 0.0");
		
		// 해결방안 #2. 
		double avg = list.stream()
			.mapToInt(Integer::intValue)
			.average()
			.orElse(0.0); 
		System.out.println("평균2 : " + avg);
		
		// 해결방안 #3. 
		list.stream()
		.mapToInt(Integer::intValue)
		.average()
		.ifPresent(a -> System.out.println("평균3 : " + a));
		// 값이있으면 출력, 지금은 값이 없어서 출력X 
		
		
		
		
		
	}
}
