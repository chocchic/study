package day15;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamEx07 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		
		// int[] -> DoubleStream
		IntStream intStream = Arrays.stream(arr);
		intStream.asDoubleStream().forEach(d -> System.out.println(d));
		
		System.out.println();
		
		// boxed
		intStream = Arrays.stream(arr);
		intStream.boxed().forEach(obj -> System.out.println(obj.intValue()));
	}
}
