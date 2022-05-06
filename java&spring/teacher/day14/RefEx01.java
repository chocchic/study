package day14;

import java.util.function.IntBinaryOperator;

class Calc {
	// 클래스 메서드 
	public static int staticMethod(int x, int y) {
		return x + y; 
	}
	// 인스턴스 메서드 
	public int instanceMethod(int x, int y) {
		return x + y;
	}
}
public class RefEx01 {
	public static void main(String[] args) {

		IntBinaryOperator op; 
		
		// int applyAsInt(int a, int b)
		// 클래스 메서드 
		op = (x, y) -> Calc.staticMethod(x, y); 
		System.out.println("결과1 : " + op.applyAsInt(1, 2));
		
		op = Calc::staticMethod;
		System.out.println("결과2 : " + op.applyAsInt(3, 4));
		
		// 인스턴스 메서드 
		Calc calc = new Calc(); 
		
		op = (x, y) -> calc.instanceMethod(x, y);
		System.out.println("결과3 : " + op.applyAsInt(5, 6));
		
		op = calc::instanceMethod; 
		System.out.println("결과4 : " + op.applyAsInt(7, 8));
		
		
		
		
		
	}
}
