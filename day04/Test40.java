package day04;

import java.util.Scanner;

public class Test40 {
	public static void main(String[] args) {
		// 문제 1-1. int형 방 5개의 배열을 만들고, 10,20,30,40,50을 저장하고 출력
		int[] arr = {10,20,30,40,50};
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		/*
		int[] arr2 = new int[5]; 
		for(int i = 0; i < arr.length;i++){
			arr[i] = (i+1)*10;
			System.out.println(arr[i]);
		}
		*/
		// 문제 1-2. 위 배열을 이용하여, 인덱스 번호 1번과 3번안에 있는 데이터를 더한 값을 출력
		System.out.println(arr[1]+arr[3]);
		
		// 문제 1-3. 인덱스번호를 사용자로부터 입력받고, 해당 인덱스의 데이터를 출력. 
		Scanner sc = new Scanner(System.in);
		System.out.print("인덱스 번호를 입력하세요 : ");
		int idx = Integer.parseInt(sc.nextLine());
		System.out.println("해당 번호의 인덱스 데이터 : " +arr[idx]);
		
	    // 문제 1-4. 배열안의 모든 요소를 다 더한 값을 출력.
		int sum = 0;
		for(int i = 0;i<arr.length;i++) {
			sum+=arr[i];
		}
		System.out.println(sum);
		
	    // 문제 1-5. 배열안에 있는 값 중 하나를 입력하면, 그 값이 위치한 인덱스번호 출력. 
		System.out.print("배열의 값 중 하나를 입력하세요 : ");
		idx = Integer.parseInt(sc.nextLine());
		for(int i = 0; i< arr.length;i++) {
			if(arr[i]==idx) {
				System.out.println(i + " 번에 위치합니다.");
				break;
			}
		}
		/*
		//값이 없을 때 print
		boolean checked = false;
		for(int i = 0; i< arr.length;i++) {
			if(arr[i]==idx) {
				checked =true;
				System.out.println(i + " 번에 위치합니다.");
				break;
			}
		}
		
		if(!checked) {
			System.out.println("입력하신 값이 배열에 존재하지 않습니다.");
		}
		*/
	    // 문제 1-6. 인덱스번호 2번과 4번방의 값을 교환해보세요. 
		int temp;
		temp = arr[2];
		arr[2]=arr[4];
		arr[4] = temp;
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	    // 문제 1-7. (심화) 내림차순으로 정렬하세요.
		//bubble sort
		for(int i = 0; i < arr.length-1;i++) {
			for(int j = i+1; j<arr.length;j++) {
				if(arr[i]<arr[j]) {
					temp = arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}	
			}
		}
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}		
		System.out.println();
		
		//선택 정렬
		for(int i = 0; i < arr.length-1;i++) {
			int max = i; // 가장 큰 값의 인덱스 번호를 들고 다닐 변수
			for(int j = i+1; j<arr.length;j++) {
				if(arr[max]<arr[j]) {
					max = j;
				}	
			}//max에 가장 큰 값의 인덱스 번호가 남아있다.
			// 찾아ㅏ놓은 큰 값이랑 i번째 앞부분 값이랑 자리 교환
			int tmp = arr[i];
			arr[i] = arr[max];
			arr[max] = tmp;
		}
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}		
		System.out.println();
	}
}
