package day07;

class Car{
	String color = "";
	Car(String color){
		this.color = color;
	}
	String drive() {
		return color +"색 차가 지나갑니다.";
	}
}

public class Test710 {
	public static void main(String[] args) {
		int[] arr = new int[5];
		for(int i = 0; i<arr.length;i++) {
			arr[i]=i+1;
		}
		String[] cols = {"빨간", "노란", "초록", "파란","보라"};
		
		Car[] cars = new Car[5]; //차고
		for(int i = 0; i<cars.length;i++) { // 이 for문 이 없어지면 객체 생성이 안되서 null pointer됨
			cars[i] = new Car(cols[i]);
		}
		for(Car c : cars) {
			System.out.println(c.drive());
		}
	}
}
