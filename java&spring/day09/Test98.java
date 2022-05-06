package day09;
// 아래 추상클래스를 상속받은 MyCalc 클래스를 구현해보세요
abstract class Calculator {
	public abstract int add(int a,int b); // 두 정수의 합을 구하여 리턴
	public abstract int substract(int a, int b); // 두 정수의 차를 구하여 리턴
	public abstract double average(int[] a); // 배열에 저장된 정수의 평균값 리턴
}

class MyCalc extends Calculator{

	@Override
	public int add(int a, int b) {
		return a+b;
	}

	@Override
	public int substract(int a, int b) {
//		if(a>b)	return a-b;
//		else return b-a;
		return a>b?a-b:b-a;
	}

	@Override
	public double average(int[] a) {
		double result = 0;
		for(int aa : a) {
			result+=aa;
		}
		result/=a.length;
		return result;
	}
}
public class Test98 {
	public static void main(String[] args) {
		MyCalc c = new MyCalc();
		System.out.println(c.add(4, 21));
		System.out.println(c.substract(4, 21));
		int[] a = {1,2,3,4,5,6,7,8,9};
		System.out.println(c.average(a));
		System.out.println(c.average(new int[] {1,2,3,4,5,6,7,8,9}));
	}
}
