package day04;

import java.util.Scanner;

public class Test44 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// * 2차원 배열 문제 * 
				//문제1. 아래와 같은 형태의 정방배열을 만들어 값을 저장하고 출력해보세요. 
				/*
					10	20	30
					40	50	60
					70	80	90
				*/
//		int[][] arr = {{10,	20,	30},{40,50,60},{70, 80, 90}};
		/*
		int[][] arr= new int[3][3];
		for(int i =0;i<3;i++){
			for(int j =0; i<3;j++){
				arr[i][j] = i*30+j*10;
		`	}
		}
		*/
//		for(int i = 0; i<arr.length;i++) {
//			for(int j =0; j<arr[i].length;j++) {
//				System.out.print(arr[i][j]+" ");
//			}System.out.println();
//		}
				//문제2. int형 3행 2열의 정방배열을 만들고, 
				//		각 방에 정수를 입력받아 저장하고 출력해보세요. 
//		int[][] arr2 = new int[3][2];
//		for(int i = 0;i<3;i++) {
//			for(int j = 0; j<2;j++) {
//				System.out.print(i+"행 "+ j +"번째 수를 입력 : ");
//				arr2[i][j] = Integer.parseInt(sc.nextLine());
//			}
//		}
//		for(int i = 0; i<arr2.length;i++) {
//			for(int j =0; j<arr2[i].length;j++) {
//				System.out.print(arr2[i][j]+" ");
//			}System.out.println();
//		}
		
		//문제3. 아래와 같은 형태의 비정방배열을 만들어 아래와 같이 값을 저장하고 출력해보세요. 
		/*
					10	11	12
					20	21
					30	31	32
					40	41
		*/
//		int arr3[][] = {{10,11,12},{20,21},{30,31,322},{40,	41}};
//		for(int i = 0; i<arr3.length;i++) {
//			for(int j =0; j<arr3[i].length;j++) {
//				System.out.print(arr3[i][j]+" ");
//			}System.out.println();
//		}
		
		// 문제4. 한층에 5호씩 3층짜리 아파트가 있다. (101호 ~ 305호) 
		//	1) 배열로 만들어서 각각 호마다 관리비를 입력받고, 전체 출력해보세요. 
			//3행 5열 아파트
		int[][] arr4 = new int[3][5];
		for(int i = 0;i<3;i++) {
			for(int j =0;j<5;j++) {
				System.out.print((i+1)+"0"+j+"호 관리비를 입력하세요 : ");
				arr4[i][j] = Integer.parseInt(sc.nextLine());
			}
		}
		System.out.println("각 호별 관리비");
		for(int i = 0; i<arr4.length;i++) {
			for(int j =0; j<arr4[i].length;j++) {
				System.out.print(arr4[i][j]+" ");
			}System.out.println();
		}
		//	2) 층별 관리비 평균을 구해서 출력해보세요. 
		/*층별 관리비 평균, 전체 평균*/
//		int total=0,tt=0;
//		for(int i = 0; i<arr4.length;i++) {
//			total=0;
//			for(int j =0; j<arr4[i].length;j++) {
//				total+=arr4[i][j];
//			}
//				System.out.println(i+"층 관리비 평균 : "+(total/5));
//				tt+=total;
//		}
//		System.out.println("전체 관리비 평균 ㅣ "+ (tt/3));
		//또는 매 total 값을 베열에 저장해서 총 관리비 평균 계산
		//	3) 전체 관리비 평균을 구해서 출력해보세요. 
//				int ttt=0;
//				for(int i = 0; i<arr4.length;i++) {
//					for(int j =0; j<arr4[i].length;j++) {
//						ttt+=arr4[i][j];
//					}
//				}
//				System.out.println("전체 관리비 평균 ㅣ "+ (ttt/3));
		//	4) 103호와 203호의 관리비가 서로 바뀌었다고합니다. 교환해주세요. 
//		int tmp = arr4[0][3];
//		arr4[0][3] = arr4[2][3];
//		arr4[2][3] = tmp;
//		System.out.println("각 호별 관리비");
//		for(int i = 0; i<arr4.length;i++) {
//			for(int j =0; j<arr4[i].length;j++) {
//				System.out.print(arr4[i][j]+" ");
//			}System.out.println();
//		}
		
		//	5) 전체 관리비 평균보다 적게 사용한 호수들을 출력해보세요. 
//		System.out.println("관리비 평균보다 낮네나온 호: ");
//		for(int i = 0; i<arr4.length;i++) {
//			for(int j =0; j<arr4[i].length;j++) {
//				if(tt > arr4[i][j])
//				System.out.println(i+"0"+j+"호");
//			}
//		}
		
		//	6) 관리비가 가장 적은 호수와 가장 많이나온 호수를 출력해보세요. 
		int max = arr4[0][0], min = arr4[0][0];
		int maxh = 0,minh =0;
		for(int i = 0; i<arr4.length;i++) {
			for(int j =0; j<arr4[i].length;j++) {
				if(min > arr4[i][j]) {
					min = arr4[i][j];
					minh = (i+1)*100+j+1;
				}
				if(max < arr4[i][j]) {
					max = arr4[i][j];
					maxh = (i+1)*100+j+1;
				}			
			}
		}
		System.out.println("가장 많이 나온 호 : " + maxh +" 금액 : " + max);
		System.out.println("가장 적게 나온 호 : " + minh +" 금액 : " + min);
		
		//	7) 관리비 적게 나온순으로 정렬해보세요. 
//		int[] house = new int[15];
//		int[] pay = new int[15];
//		for(int i = 0; i<arr4.length;i++) {
//			for(int j =0; j<arr4[i].length;j++) {
//				house[i*5+j] = (i+1)*100+j+1;
//				pay[i*5+j] = arr4[i][j];
//			}
//		}
//		for(int i = 0; i < 14;i++) {
//			for(int j = i+1;j<15;j++) {
//				if(pay[i]>pay[j]) {
//					tmp = pay[i];
//					pay[i] = pay[j];
//					pay[j] = tmp;
//				}
//			}
//		}
//		System.out.println("관리비가 적게 나온 집부터 정렬");
//		for(int i = 0;i<15;i++) {
//			System.out.println(house[i] + "호 : "+pay[i] + "원");
//		}		

	}
}

