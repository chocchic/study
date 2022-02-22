package day12;

import java.util.ArrayList;
import java.util.Scanner;

/* ArrayList
	Scanner 클래스로 -1이 입력될때까지 양의 정수를 입력받아 ArrayList에 저장하고, 
	ArrayList를 검색하여 가장 큰 수를 출력하는 프로그램을 작성하세요.
*/
public class CollEx02 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> iint = new ArrayList<>();
		while(true) {
			System.out.println("저장할 수를 입력하세요 >> ");
			int i = Integer.parseInt(sc.nextLine());
			if(i != -1) {
				iint.add(i);
			}else {
				System.out.println("저장 종료");
				break;
			}
		}
		
		int m = 0;
		
		for(int i : iint) {
			if(m < i) m = i;
		}
		System.out.println("최대값 : " + m);
		
	}
}
