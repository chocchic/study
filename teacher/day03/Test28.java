package day03;

public class Test28 {
	public static void main(String[] args) {

		// 배열 선언, 생성 
		int a; 
		//System.out.println(a);
		a = 10;

		int [] arr;
		//System.out.println(arr);
		arr = new int[3]; // 3개짜리 방 만들겠다. 
		
		// 선언 + 생성 (* 가장많이 사용하는 형태) 
		int [] arr2 = new int[5];
		
		
		// 값저장 
		arr[0] = 10; 
		arr[1] = 20; 
		arr[2] = 30; 
		
		// 출력 확인
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		
		System.out.println(arr);
		
		
		//String str = arr; 
		//int num = arr; // 주소를 정수형변수에 저장 불가능 
		int num = arr[0]; 
		System.out.println(num);
		
		System.out.println(arr.length);
		
	
		
		System.out.println("---------------------------");
		
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (i+1)*10;
		}
		
		System.out.println("---------------------------");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
