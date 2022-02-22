package day12;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayTest03 {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(5);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(0);
		arr.add(1);
		
		System.out.println(arr);
		ArrayList<Integer> list = new ArrayList<Integer>(arr.subList(1, 4));
		System.out.println(list);
		
		// 정렬
		Collections.sort(arr);
		System.out.println(arr);
		
//		list.add(1000);
		System.out.println(arr.containsAll(list)); // 1000이 추가되는 경우 전부포함 X
		
		// list와 중복되는 값을 남기고 모두 삭제
		System.out.println(arr.retainAll(list));
		System.out.println(arr);
	}
}
