package day15;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamEx15 {
	public static void main(String[] args) {

		List<Person> list = Arrays.asList(
				new Person("aaa", 10, Person.Gender.MALE),
				new Person("bbb", 20, Person.Gender.FEMALE),
				new Person("ccc", 5, Person.Gender.MALE),
				new Person("ddd", 10, Person.Gender.FEMALE)
		);
		
		// 여자만 수집 
		
		// 풀어서 작성 
		Stream<Person> orgStream = list.stream(); 
		Stream<Person> femaleStream = orgStream.filter(p -> p.getGender() == Person.Gender.FEMALE); 
		
		Supplier<FemalePerson> supplier = () -> new FemalePerson(); 
		BiConsumer<FemalePerson, Person> accumulator = (fp, p) -> fp.accumulate(p); 
		BiConsumer<FemalePerson, FemalePerson> combiner = (fp1, fp2) -> fp1.combine(fp2);
		
		//<Object> Object java.util.stream.Stream.collect(
		//		Supplier<Object> supplier, 
		//		BiConsumer<Object, ? super Person> accumulator, 
		//		BiConsumer<Object, Object> combiner)
		//			1.최종공급할객체, 2.수집메서드, 3.결합시켜주는메서드
		FemalePerson femalePerson = femaleStream.collect(supplier, accumulator, combiner);
		
		// 확인 출력 
		femalePerson.getList()
		.stream()
		.forEach(p -> System.out.println(p.getName() + " "+ p.getGender()));
		
		// 변수없이 줄여서 작성 
		FemalePerson fPerson = list.stream()
		.filter(p -> p.getGender() == Person.Gender.FEMALE)
		.collect(
				() -> new FemalePerson(),
				(fp, p) -> fp.accumulate(p),
				(fp1, fp2) -> fp1.combine(fp2)
		);
		
		// 람다 메서드참조로 변경 
		FemalePerson fP = list.stream()
		.filter(p -> p.getGender() == Person.Gender.FEMALE)
		.collect(
				FemalePerson::new,
				FemalePerson::accumulate,
				FemalePerson::combine
		);
		
		fP.getList().stream()
		.forEach(p -> System.out.println(p.getName()));
		
		
		
		
		
		
		
		
		
		
		
	}
}
