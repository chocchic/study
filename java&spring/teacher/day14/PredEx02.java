package day14;

import java.util.function.IntPredicate;

public class PredEx02 {
	public static void main(String[] args) {

		// boolean test(int i) 
		
		// 2의 배수 검사 
		IntPredicate predA = (a) -> a % 2 == 0; 
		
		// 3의 배수 검사 
		IntPredicate predB= (a) -> a % 3 == 0; 
		
		IntPredicate predAB; 
		boolean result; 
		
		predAB = predA.and(predB); 
		result = predAB.test(9);  // 12는 true 나옴 
		System.out.println("9는 2와 3의 배수 인가용?" + result);
		
		predAB = predA.or(predB); 
		result = predAB.test(9); 
		System.out.println("9는 2 또는 3의 배수 인가요?" + result);
		
		predAB = predA.negate(); 
		result = predAB.test(9);  // %2 == 0 -> false -> true
		System.out.println("9는 홀수 인가요?" + result);
		
		
		
		
		
	}
}
