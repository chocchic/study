package day15;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx04 {
	public static void main(String[] args) {

		List<Member> list = Arrays.asList(
				new Member("aaa", Member.MALE, 30), 
				new Member("bbb", Member.FEMALE, 20), 
				new Member("ccc", Member.MALE, 45), 
				new Member("ddd", Member.FEMALE, 38) 
		);
		// 남자 회원의 평균 나이 구하기 
		// 하나씩 작성 
		Stream<Member> stream = list.stream(); 
		// boolean test(T t) 
		Stream<Member> maleStream = stream.filter(m -> m.getGender() == Member.MALE); 
		IntStream ageStream = maleStream.mapToInt(Member::getAge); 
		OptionalDouble op = ageStream.average(); 
		double avg = op.getAsDouble(); 
		System.out.println("남자 평균 나이 : " + avg);
		
		double maleAvg = list.stream().filter(m -> m.getGender() == Member.FEMALE)
		.mapToInt(Member::getAge).average().getAsDouble(); 
		System.out.println("남자 평균 연령 : " + maleAvg);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
