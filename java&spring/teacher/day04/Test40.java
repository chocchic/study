package day04;

import java.util.Scanner;

public class Test40 {
	public static void main(String[] args) {

		//문제1-1. int형 방5개의 배열을 만들고, 10,20,30,40,50을 저장하고, 출력. 
		//int [] arr = {10,20,30,40,50}; 
		/*
		for(int i = 0; i < arr.length; i++) {
			arr[i] = (i + 1) * 10;
			System.out.println(arr[i]);
		}
		
		//문제1-2. 위 배열을 이용하여, 인덱스번호 1번과 3번안에 있는 데이터를 더한값을 출력. 
		//System.out.println(arr[1] + arr[3]);
		
		//문제1-3. 인덱스번호를 사용자로부터 입력받고, 해당 인덱스의 데이터를 출력. 
		Scanner sc = new Scanner(System.in);
		System.out.println("인덱스 번호 : ");
		int idx = Integer.parseInt(sc.nextLine()); // 인덱스번호 입력받기
		System.out.println(arr[idx]);
		
		//문제1-4. 배열안의 모든 요소를 다 더한 값을 출력.
		int tot = 0; 
		for(int i = 0; i < arr.length; i++) {
			tot += arr[i];  // tot = tot + arr[i];
		}
		System.out.println("total : " + tot);
		
		//문제1-5. 배열안에 있는 값중 하나를 입력하면, 그 값이 위치한 인덱스번호 출력. 
		Scanner sc = new Scanner(System.in);
		System.out.println("10~50 중 값 입력 : ");
		int val = Integer.parseInt(sc.nextLine());
		boolean checked = false;
		for(int i = 0; i < arr.length; i++) {
			if(val == arr[i]) {
				checked = true; 
				System.out.println("인덱스번호 : " + i);
				break;
			}
		}
		if(!checked) {
			System.out.println("입력하신 값은 배열에 존재하지 않습니다. ");
		}
		
		//문제1-6. 인덱스번호 2번과 4번방의 값을 교환해보세요.
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
		int tmp = arr[2]; 
		arr[2] = arr[4]; 
		arr[4] = tmp; 
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.println();
		*/
		
		//문제1-7. (심화) 내림차순으로 정렬하세요. 
		
		
		/* 버블 정렬 
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] < arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}*/
		
		int [] arr = {10,20,50,40,30}; 
		// 	     		           m
		//			            i
		//			  50 40 30 10 20 
		//							|
		//			              j
		//
		//
		// 선택정렬 
		for(int i = 0; i < arr.length-1; i++) {
			int max = i; // 가장 큰 값의 인덱스번호를 들고다닐변수 
			for(int j = i+1; j < arr.length; j++) {
				if(arr[max] < arr[j]) {
					max = j; 
				}
			}// max에 가장큰 값의 인덱스번호가 남아있다. 
			// 찾아놓은 큰값이랑 i번째 앞부분값이랑 자리교환 
			int tmp = arr[i]; 
			arr[i] = arr[max];
			arr[max] = tmp;
		}
		// 출력해서 확인 
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		
		
		
		
		
		
		
		
		
		//sc.close();
		
		
		
		
		
	}
}
