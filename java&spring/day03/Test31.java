package day03;

public class Test31 {
	public static void main(String[] args) {
		// 레퍼런스 치환(배열 공유)
		// 다수의 레퍼런스 변수가 하나의 배여려 공간을 동시에 가르키는 형태
		
		int arr[] = {1,2,3,4,5}; //stack에 arr 저장, heap에 12345 저장
		int myArr[] = arr; //주소를 복사해옴, arr이 힙과의 연결 끊겨도 유지됨.
		
		for(int i = 0; i<myArr.length;i++) {
			System.out.println(myArr[i]);
		}
		
		
	}
}
