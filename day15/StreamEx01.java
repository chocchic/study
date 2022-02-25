package day15;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx01 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("aaa","abbbb","ccc");
		
		//Iterator
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String str = it.next();
			System.out.println(str);
		}
		
		//Stream
		Stream<String> stream = list.stream(); //Stream 반복자 얻기
		stream.forEach(str -> System.out.println(str)); // foreach안에는 세미콜론 X
		
		/*
		 *  컬렉션의 stream() 메서드로 스트림 객체(반복자) 얻고, stream.forEach(람다); 메서드를 통해
		 *  컬렉션의 요소를 하나씩 접근해 사용 가능.
		 *  
		 *  forEach(Consumer<T> action
		 *  	void apply(T t) 추상 오버라이드 <-- 람다 
		 */
	}
}
