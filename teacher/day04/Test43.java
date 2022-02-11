package day04;

import java.util.Scanner;

public class Test43 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// * 1차원 배열 문제 * 
		// 문제1-1. arr이라는 이름의 방5개짜리 배열에 
		//			사용자로부터 서로다른 5개의 정수를 입력받아, 출력해보세요.
		/*
		int[] arr = new int[5]; 
		for(int i = 0; i < arr.length; i++) {
			System.out.println("정수 입력>>");
			arr[i] = Integer.parseInt(sc.nextLine());
		}
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	
		// 문제1-2. 위에서 만든 배열 arr에 저장된 값들을 오름차순으로 정렬해보세요. 
		int [] arr = {5,8,7,3,1};
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					int tmp = arr[i]; 
					arr[i] = arr[j];
					arr[j] = tmp; 
				}
			}
		}
		for(int a : arr) {
			System.out.print(a + " ");
		}
		System.out.println();
		
		
		// 문제1-3. 정수 한개를 더 입력받아 저장해보세요. 
		//			입력받은 정수는 기존배열의 마지막 데이터 다음 방에 저장되어야한다. 
		//			단, 배열은 문법적으로 방크기 조절불가.... 
		//			결국 arr의 방 크기는 6이 되어야함. 
		int [] arr = {5,8,7,3,1};
		System.out.println("arr 주소 : " + arr);
		
		int [] tmp = arr;  // 배열 원본 잠시 주소 복사 
		System.out.println("tmp 주소 : " + tmp);
		
		arr = new int[6];  // arr에 새로운방 6개 만들어서 주소 담기 
		System.out.println("new arr주소 : " + arr);
		
		int len = arr.length;  // 방 6개로 새로만든 방의 개수 담아두기 
		for(int i = 0; i < tmp.length; i++) { // 원본 데이터를 새로운 arr방에 복사 
			arr[i] = tmp[i]; 
		}
		
		System.out.println("정수 추가 입력>>");
		int num = Integer.parseInt(sc.nextLine());
		arr[len-1] = num;
		
		for(int a : arr) {
			System.out.println(a);
		}
		*/
		
		
		// 문제2-1. 게임 랭킹 보드. 5개의 데이터를 저장할 수 있는 users와 scores라는 배열이 있고, 
		//		users배열에는 유저네임, scores 배열에는 유저의 게임점수를 입력받는다. 
		//		단, 유저네임과 게임점수는 배열 인덱스상으로 서로 일치해야한다. 
		/*
		String [] users = new String[5]; 
		int[] scores = new int[5]; 
		for(int i = 0; i < users.length; i++) {
			System.out.println("이름>>");
			users[i] = sc.nextLine();
			System.out.println("점수>>");
			scores[i] = Integer.parseInt(sc.nextLine());
		}
		*/
		// 문제2-2. 위 저장된 users와 scores를 아래와 같은 형태로 출력해보세요. 
		/*		출력예)
					user_name		score
					----------------------
					피카츄			 87
					파이리			 24
					....
		
		System.out.println("user_name\t score");
		System.out.println("----------------------");
		for(int i = 0; i < users.length; i++) {
			System.out.println(users[i] + "\t\t" + scores[i]);
		}*/
		 

		// 문제2-3. 두번째 유저의 점수와 세번째 유저의 점수가 바뀌어 저장되었다 하네요.
		//			교환해서 저장해주세요. 
		/*
		int tmp = scores[1]; 
		scores[1] = scores[2];
		scores[2] = tmp; 
		for(int i = 0; i < users.length; i++) {
			System.out.println(users[i] + "\t\t" + scores[i]);
		}*/
		String[] users = {"a","b","c","d","e"};
		int[] scores = {100,20,43,55,90};
		
		// 문제2-4. 점수가 높은순으로 게임 랭킹보드를 만들고, 출력해주세요. (1~5위) 
		/*		출력예)
		 			rank		user_name		score
		 			---------------------------------
		 			1			꼬북이			97
		 			2			피카츄			87
		 			3...
		
		for(int i = 0; i < scores.length-1; i++) {
			for(int j = i + 1; j < scores.length; j++) {
				if(scores[i] < scores[j]) {
					int tmp = scores[i];
					scores[i] = scores[j];
					scores[j] = tmp; 
					String stmp = users[i]; 
					users[i] = users[j]; 
					users[j] = stmp;
				}
			}
		}
		System.out.println("rank\t user_name\t score");
		System.out.println("---------------------------------");
		for(int i = 0; i < users.length; i++) {
			System.out.println((i+1) + "\t" + users[i] + "\t\t" + scores[i]);
		}
*/
		// 문제2-5. 또 한명의 유저가 게임을 끝냈습니다. 
		//			점수와 유저네임을 입력받고, 기존의 게임 랭킹보드를 업데이트 해주세요. 
		//			이때 입력받은 새로운 유저의 데이터는 기존데이터에 추가.(1-3문제참고) 
		//			저장 후, 랭킹을 출력하시되 1~5위까지만 출력해주세요. 
		
		// 배열 방 크게 만들기 
		int len = scores.length; // 기존 방크기 담아놓기 : 5 
		int [] scoTmp = scores;  // 임시 배열에 복사 
		String[] usrTmp = users; 
		scores = new int[len + 1]; // 기존크기보다 1개방 더 크게 새로 만들기  
		users = new String[len + 1]; 
		// 다시 큰방에 옮겨 담기 
		for(int i = 0; i < len; i++) {
			scores[i] = scoTmp[i];
			users[i] = usrTmp[i]; 
		}
		// 새이름과 점수 입력받아 마지막 방에 저장 
		System.out.println("이름>>");
		users[len] = sc.nextLine();
		System.out.println("점수>>");
		scores[len] = Integer.parseInt(sc.nextLine());
		
		// 정렬 다시 
		for(int i = 0; i < scores.length-1; i++) {
			for(int j = i + 1; j < scores.length; j++) {
				if(scores[i] < scores[j]) {
					int tmp = scores[i];
					scores[i] = scores[j];
					scores[j] = tmp; 
					String stmp = users[i]; 
					users[i] = users[j]; 
					users[j] = stmp;
				}
			}
		}
		
		// 순위 출력 
		System.out.println("rank\t user_name\t score");
		System.out.println("---------------------------------");
		for(int i = 0; i < 5; i++) {
			System.out.println((i+1) + "\t" + users[i] + "\t\t" + scores[i]);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		sc.close();
		
		
	}
}
