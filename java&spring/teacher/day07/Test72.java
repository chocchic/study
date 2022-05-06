package day07;

// 클래스 초기화 블럭 
public class Test72 {
	
	static int [] arr = new int [10]; 
	
	static {
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (int)(Math.random() * 10) + 1; 
		}
	}
	
	public static void main(String[] args) {

		System.out.println("main 시작");
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		
	}
}
