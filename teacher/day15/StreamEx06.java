package day15;

import java.util.Arrays;
import java.util.List;

public class StreamEx06 {
	public static void main(String[] args) {

		// flapMapXXX() 
		List<String> list = Arrays.asList("java8 lamda", "stream mapping"); 
		list.stream()
		.flatMap(data -> Arrays.stream(data.split(" ")))
		.forEach(word -> System.out.println(word));
		
		System.out.println();
		
		List<String> list2 = Arrays.asList("10, 20, 30", "40, 50, 60"); 
		list2.stream()
		.flatMapToInt(data -> {
			String[] strArr = data.split(","); 
			int[] arr = new int[strArr.length]; 
			for(int i = 0; i < strArr.length; i++) {
				arr[i] = Integer.parseInt(strArr[i].trim());
			}
			return Arrays.stream(arr);
		}).forEach(number -> System.out.println(number));
		
		// mapXXX() 
		List<Student> stdList = Arrays.asList(
				new Student("aaaa", 10),
				new Student("bbbb", 20),
				new Student("cccc", 30),
				new Student("dddd", 40)
		);

		stdList.stream()
		.mapToInt(Student::getScore)
		.forEach(score -> System.out.println(score));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
