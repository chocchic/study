package day03;

public class Test29 {
	public static void main(String[] args) {
		double[] dArr = new double[5];
		dArr[0] = 1.234;
		dArr[1] = 3;
		System.out.println(dArr[1]);
		
		float[] fArr = new float[3];
		fArr[0] = 3.14F;
		
		char[] chArr = new char[5];
		chArr[0] = 'a';
		
		String[] strArr = new String[3];
		strArr[0] = "피카츄";
		System.out.println(strArr[0]);
		
		int[] score = {10,20,30,40,50};
		int[] score2 = new int[]{10,20,30,40,50}; // 가능
		
		System.out.println(score[0]);
//		System.out.println(score[5]); // error, indeex out of bound. last idx는 4
		
		// 문제 : 최대값과 최소값을 구해보세요
		int[] arr = {79,85,12,120,4,95};
		int max=0,min=79;
		for(int i = 0; i< arr.length;i++) {
			if(max<arr[i]) max = arr[i];
		}
		System.out.println("최대값 : " + max);
			
		for(int i = 0; i< arr.length;i++) {
			if(min>arr[i]) min = arr[i];
			
		}
		System.out.println("최소값 : " + min);
		
		//축약
		for(int i = 0; i< arr.length;i++) {
			if(max<arr[i]) max = arr[i];
			if(min>arr[i]) min = arr[i];
			
		}
		System.out.println("최대값 : " + max);
		System.out.println("최소값 : " + min);
	}
}
