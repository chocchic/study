package day03;

public class Test31 {
	public static void main(String[] args) {

		// 레퍼런스 치환(배열 공유) 
		// : 다수의 레퍼런스변수가 하나의 배열 공간을 동시에 가르키는 형태 
		
		int[] arr = {1,2,3,4,5}; 
		int[] myArr = arr; 
		
		for(int i = 0; i < myArr.length; i++) {
			System.out.println(myArr[i]);
		}
		
		
		
		
		
		
	}
}
