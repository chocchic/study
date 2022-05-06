package day15;

import java.util.Arrays;

public class StreamEx10 {
	public static void main(String[] args) {
		int[] arr = {2,4,6};
		boolean isEven = Arrays.stream(arr).allMatch(n -> n %2 == 0);
		System.out.println("모두 2의 배수이니? "+ isEven); // 배열의 모든 요소가 match 되면 true
		
		boolean times3 = Arrays.stream(arr).anyMatch(a -> a%3 ==0);
		System.out.println("3의 배수가 있니? " + times3);
		
		boolean nonetimes3 = Arrays.stream(arr).noneMatch(a -> a%3 == 0);
		System.out.println("3의 배수가 없니? " + nonetimes3);
	}
}
