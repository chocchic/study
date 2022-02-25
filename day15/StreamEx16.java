package day15;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx16 {
	public static void main(String[] args) {
		List<Person> list = Arrays.asList(
				new Person("aaa", 10, Person.Gender.MALE, Person.City.Seoul),
				new Person("bbb", 20, Person.Gender.FEMALE, Person.City.Pusan),
				new Person("ccc", 5, Person.Gender.MALE,Person.City.Pusan),
				new Person("ddd", 10, Person.Gender.FEMALE,Person.City.Seoul)
				);
		
		// 사람들을 성별로 그룹핑하고, 같은 그룹에 속하는 사람 List를 생성한 후 성별을 키로, 사람 list를 값으로 같즞 Map을 얻어보자
		Stream<Person> orgStream = list.stream();
		//Person 주고 Gender줘(그룹핑 기준)
		Function<Person, Person.Gender> classifier = Person::getGender;
		// Person 주고, 생략, Map으로 줘 Map의 키는 Gender고, 값은 Person이 들어있는 List
		Collector<Person, ?,Map<Person.Gender, List<Person>>> collector = Collectors.groupingBy(classifier);
		Map<Person.Gender, List<Person>> mapList = orgStream.collect(collector);
		
		// 줄여서 작성
		list.stream().collect(Collectors.groupingBy(Person::getGender));
		
		// 출력 확인
		System.out.println(" *** 남그룹 *** ");
		mapList.get(Person.Gender.MALE).stream().forEach(p -> System.out.println(p.getName() + " " + p.getGender()));
		
		System.out.println(" *** 여그룹 *** ");
		mapList.get(Person.Gender.FEMALE).stream().forEach(p -> System.out.println(p.getName() + " " + p.getGender()));
		
		// 풀어서
		Stream<Person> orgStr = list.stream();
		// 그룹핑 할 기준 : City 참고
		Function<Person, Person.City> classifier2 = Person::getCity;
		// 이름으로 매핑해서 수집할 수 있게 기준 
		Function<Person, String> mapper = Person::getName;
		//이름을 리스트로 수집해 줄 컬렉터
		Collector<String,?,List<String>>collector3 = Collectors.toList();
		// mapper(이름)을 기준으로 매핑해서, 리스트로 수집해올 컬렉터
		Collector<Person,?,List<String>> collector2 = Collectors.mapping(mapper, collector3);
		// * 원하는 그룹으로 수집할 수 있는 수집기 만듦
		Collector<Person, ? , Map<Person.City, List<String>>> collector4 = Collectors.groupingBy(classifier2, collector2);
		// * 최종 수집
		Map<Person.City, List<String>> mapByCity = orgStr.collect(collector4);
		// 줄여서
		list.stream().collect(Collectors.groupingBy(
				Person::getCity,
				Collectors.mapping(Person::getName, Collectors.toList())
				));
		
		//출력 확인
		System.out.println("** 서울 **");
		mapByCity.get(Person.City.Seoul).stream().forEach(p->System.out.println(p));
		System.out.println("** 부산 **");
		mapByCity.get(Person.City.Pusan).stream().forEach(p->System.out.println(p));
	}
}
