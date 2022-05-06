package day09;
// 아래 추상클래스를 상속받은 MyCalc 클래스를 구현하세요. 
abstract class Calculator {
	abstract int add(int a, int b);			// 두 정수의 합을 구하여 리턴 
	abstract int subtract(int a, int b);	// 두 정수의 차를 구하여 리턴 
	abstract double average(int [] a);		// 배열에 저장된 정수의 평균값 리턴 
}
class MyCalc extends Calculator {
	@Override
	int add(int a, int b) {
		return a + b;
	}
	@Override
	int subtract(int a, int b) {
		return a - b;
	}
	@Override
	double average(int[] a) {
		double sum = 0; 
		for(int i : a) { sum += i; }
		return sum / a.length;
	}
}
public class Test118 {
	public static void main(String[] args) {

		MyCalc c = new MyCalc();
		System.out.println(c.add(10, 20));
		System.out.println(c.subtract(100, 90));
		System.out.println(c.average(new int[] {1,2,3,4,5}));
		int[] arr = new int[]{1,2,3,4,5}; 
		
	}
}








