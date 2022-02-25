package day15;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx04 {
	public static void main(String[] args) {
		List<Member> list = Arrays.asList(new Member("aaa", Member.MALE, 30), new Member("bbb", Member.FEMALE, 20),
				new Member("ccc", Member.MALE, 45), new Member("ddd", Member.FEMALE, 38));
		
		// 남자 회원의 평균 나이 구하기
		// 하나씩 작성
		Stream<Member> stream = list.stream();
		// boolean test(T t)
		Stream<Member> maleStream = stream.filter(m -> m.getGender() == Member.MALE); // 멤버가 남성이면 true 리턴
		IntStream ageStream = maleStream.mapToInt(Member::getAge);
		OptionalDouble op = ageStream.average();
		double age = op.getAsDouble(); // 이거 안해주면 OptionalDouble[실수] 이런식으로 출력됨
		System.out.println("남자 평균 나이 : " + age);
		// 한줄 축약
		double maleAge = list.stream().filter(m->m.getGender() == Member.MALE).mapToInt(Member::getAge).average().getAsDouble();
		System.out.println("남자 평균 연령 : "  + maleAge);
		
		//여자 MALE을 FEMALE로만 바꿔주면 됨
		double femaleAvg = list.stream().filter(m->m.getGender() == Member.FEMALE).mapToInt(Member::getAge).average().getAsDouble();
		System.out.println("여자 평균 연령 : " + femaleAvg);
	}
}
