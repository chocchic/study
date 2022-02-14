package day06;

class Circle {
	int radius = 30; 	
}
public class Test64 {
	// 편의상 객체생성없이 바로 사용가능하도록 static 메서드 만든다. 
//	static void plus(int num) {
//		num = num + 1; 
//	}
	static int plus(int num) {
		num = num + 1; 
		return num; 
	}
	static void biggerPizza(Circle pizza) {
		System.out.println("bigger : " + pizza);
		pizza.radius++;
	}
	static void increase(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			arr[i]++; 
		}
	}
	public static void main(String[] args) {
		// 배열 
		int [] arr = {1,2,3,4,5}; 
		increase(arr); 
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
		
		// 기본형 
		int num = 10; 
		//plus(num); 
		num = plus(num); 
		System.out.println(num);
		
		// 참조형 
		Circle pizza = new Circle(); 
		System.out.println("main : "+ pizza);
		biggerPizza(pizza); 
		System.out.println(pizza.radius);
		
		
		
		
	}
}
