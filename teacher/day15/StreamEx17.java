package day15;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamEx17 {
	public static void main(String[] args) {

		List<Person> list = Arrays.asList(
				new Person("aaa", 10, Person.Gender.MALE),
				new Person("bbb", 20, Person.Gender.FEMALE),
				new Person("ccc", 5, Person.Gender.MALE),
				new Person("ddd", 10, Person.Gender.FEMALE)
		);
		
		// 사람들을 성별로 그룹핑하고, 같은 그룹에 속하는 사람들의 평균나이를 구하고 
		// 성별을 키로 평균값을 값으로 갖는 Map 만들기 
		
		Map<Person.Gender, Double> mapGender 
				= list.stream()
				.collect(
						Collectors.groupingBy(
								Person::getGender,
								Collectors.averagingDouble(Person::getAge)
						)
				);
		
		System.out.println("남자 나이 평균 : " + mapGender.get(Person.Gender.MALE));
		System.out.println("여자 나이 평균 : " + mapGender.get(Person.Gender.FEMALE));
		
		// 성별로 그룹핑, 같은 그룹에 속하는 학생이름을 쉼표로 구분해서 문자열만들고,
		// 성별을 키, 이름 문자열을 값으로 갖는 Map 만들기 
		Map<Person.Gender, String> mapName = list.stream()
				.collect(
						Collectors.groupingBy(
								Person::getGender, 
								Collectors.mapping(Person::getName, Collectors.joining(","))
						)
				); 
		
		System.out.println("남자 전체 이름 : " + mapName.get(Person.Gender.MALE));
		System.out.println("여자 전체 이름 : " + mapName.get(Person.Gender.FEMALE));
		
		
		
		
		
		
	}
}
