package day14;

import java.util.function.IntPredicate;

public class PredEx02 {
	public static void main(String[] args) {
		// 2의 배수 검사
		//boolean test(int i);
		IntPredicate predA = a-> a % 2 == 0; // = (a) ->{return 2%2==0;};
		
		// 3의 배수 검사
		IntPredicate predB = a-> a % 3 == 0;
		
		IntPredicate predAB;
		boolean result;
		int q = 24;
		
		predAB = predA.and(predB);
		result = predAB.test(q);
		System.out.println(q+"는 2와 3의 배수인가요? " + result);
		
		predAB = predA.or(predB);
		result = predAB.test(q);
		System.out.println(q + "는 2 또는 3의 배수인가요? " + result);
		
		predAB = predA.negate();
		result = predAB.test(q);
		System.out.println(q+ "는 홀수 인가요? " + result);
	}
}
