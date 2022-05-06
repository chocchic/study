package day15;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx14 {
	public static void main(String[] args) {

		List<Person> list = Arrays.asList(
				new Person("aaa", 10, Person.Gender.MALE),
				new Person("bbb", 20, Person.Gender.FEMALE),
				new Person("ccc", 5, Person.Gender.MALE),
				new Person("ddd", 10, Person.Gender.FEMALE)
		);

		// Person들 중 남자만 필터링해 별도의 List생성 
		// 풀어서 작성 
		Stream<Person> orgStream = list.stream(); 
		Stream<Person> maleStream = orgStream.filter(p -> p.getGender() == Person.Gender.MALE);
		Collector<Person, ?, List<Person>> collector = Collectors.toList(); 
		List<Person> maleList = maleStream.collect(collector); 
		
		maleList.stream()
		.forEach(l -> System.out.println(l.getName() + " " + l.getGender()));
		
		System.out.println("--------------");
		// 줄여서 
		List<Person> femaleList = list.stream()
				.filter(p -> p.getGender() == Person.Gender.FEMALE)
				.collect(Collectors.toList()); 
		
		femaleList.stream()
		.forEach(l -> System.out.println(l.getName() + " " + l.getGender()));
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
