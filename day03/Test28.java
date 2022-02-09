package day03;

public class Test28 {
	public static void main(String[] args) {
		// 배열 선언, 생성
		int a;
		a = 10;
		
		int [] arr; // stack 메모리에 arr[]이 만들어짐. null 상태
		arr = new int[3]; // heap 메모리에 3개짜리 방 만들겠다.
		
		//선언 + 생성
		int[] arr2 = new int[5];
		
		//값 저장
		arr[0] = 10;
		arr[1] = 20;
		arr[2] = 30;
		
		for(int i =0;i<3;i++) {
			arr[i]=(i+1)*10;
		}
		
		//출력 확인
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr); // 주소값나옴
		
		//int num = arr; // error, 주소를 정수형 변수에 저장할 수 없음
		int num = arr[0];
		System.out.println(num);
		
		System.out.println(arr.length);
		for(int i =0;i<arr.length;i++) {
			arr[i]=(i+1)*10;
		}
		
		
	}
}
