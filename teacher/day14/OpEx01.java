package day14;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;

public class OpEx01 {
	
	private static int[] score = { 95, 98, 97 };
	
	// int 두개 받아 연산하고 int한개 리턴해주는 IntBinaryOperator
	public static int maxOrMin(IntBinaryOperator operator) {
		int result = score[0]; 
		for(int sco : score) {
			result = operator.applyAsInt(result, sco); 
		}
		return result;
	}

	public static void main(String[] args) {

		// 최대값 
		int max = maxOrMin(
				(a, b) -> {
					if(a >= b) return a; 
					else return b;
				}
		);
		System.out.println("최대값 : " + max);
		
		// 최소값 
		int min = maxOrMin(
				(a, b) -> {
					if(a <= b) return a; 
					else return b;
				}
		); 
		System.out.println("최소값 : " + min);
		
		
		
		
		
		
	}
}
