package day12;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayTest03 {
	public static void main(String[] args) {

		ArrayList<Integer> arr = new ArrayList<Integer>();
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
		
		// list가 가진 요소들이 전부 arr에 있으면 true 
		//list.add(1000);
		System.out.println(arr.containsAll(list)); 
		
		// list와 중복되는 값을 남기고 나머지 모두 arr에서 삭제 
		System.out.println(arr.retainAll(list));
		System.out.println(arr);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
