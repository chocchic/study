package day04;

public class Test42 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		
		for(int i = 0;i<arr.length;i++) {
			System.out.println(arr[i]);
		}
		
		/* for each문, 업그레이드 for문
		for(타입 변수명 : 배열)}
		 	//반복해서 실행할 명령문...
		}
		//배열의 크기만큼 자동으로 for문이 반복하고, 매회 반복할 때 마다 앞에 선언한 변수에 해당 회차에 맞는 방의 값을 넣어주낟.
		*/
		//for each문, 업그레이드 for문
		for(int a: arr) {
			System.out.println(a);
		}
		String[] strs= {"ABC","DEF","GHI"};
		
		for(String s: strs) {
			System.out.println(s);
		}
		
		double[] dArr = {1.11, 2.22, 3.33,4.44};
		for(double d: dArr) {
			System.out.println(d);
		}
		
	}
}
