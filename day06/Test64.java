package day06;

class Circle{
	int radius = 30;
}

public class Test64 {
	//편의상 객체생성없이 바로 사용 가능하도록 static메서드로 만든다
	static int plus(int num) {
		num++;
		return num;
	}
	static void biggerPizza(Circle pizza) {
		pizza.radius++;
	}
	static void increase(int[] arr) {
		for(int i = 0;i<arr.length;i++) {
			arr[i]++;
		}
	}
	public static void main(String[] args) {
		//기본형
		int num = 10;
		num = plus(num);
		System.out.println(num);
		
		//참조형
		Circle pizza = new Circle();
		biggerPizza(pizza);
		System.out.println(pizza.radius);
		// 배열
		int[] arr = {1,2,3,4,5};
		increase(arr);
		for(int i = 0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
	}
}
