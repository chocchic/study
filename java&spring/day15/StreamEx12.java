package day15;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class StreamEx12 {
	public static void main(String[] args) {
		// 요소가 없을 때 예외 발생 예시
		List<Integer> list = new ArrayList<Integer>();
//		double avg = list.stream().mapToInt(Integer::intValue).average().getAsDouble(); // 요소가 없을 때 getAsDouble에서 에러 발생
		
		// 해결방안 # 1.
		OptionalDouble op = list.stream().mapToInt(Integer::intValue).average();
		if(op.isPresent()) System.out.println("Average : " + op.getAsDouble());
		else System.out.println("평균 : 0.0"); // 값이 없을 떄 그냥 출력
		
		// 해결방안 #2.
		double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0.0); // 값이 존재하지않으면 0.0처리를 해라
		System.out.println("평균 : " + avg);
		
		// 해결방안 #3.
		list.stream().mapToInt(Integer::intValue).average().ifPresent(n -> System.out.println("평균 : " + n));
		// 값이 있으면 ifPresent의 함수 싫팽. 값이 없어서 실행 X
	}
}
