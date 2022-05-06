package day12;

import java.util.ArrayList;

public class ArrayTest01 {
	public static void main(String[] args) {

		// ArrayList 생성 
		ArrayList<Integer> arr = new ArrayList<Integer>();
		//ArrayList<int> arr2 = new ArrayList<int>(); 기본형X -> Wrapper 
		
		// 요소 삽입 
		arr.add(new Integer(10)); 
		arr.add(new Integer(-5)); 
		arr.add(new Integer(200)); 
		
		arr.add(20);
		arr.add(30);
		arr.add(40);
		arr.add(null); // null 삽입 가능 주의 
		
		arr.add(2, 1010); 
		System.out.println(arr);
		
		// 요소 꺼내기 
		int val = arr.get(0); 
		System.out.println(val);
		
		// 저장된 요소의 개수 
		int size = arr.size(); 
		System.out.println(size);
		
		// 요소 삭제 
		arr.remove(1); 
		System.out.println(arr);
		
		size = arr.size(); 
		System.out.println(size);
		
		// 값 변경 
		int prev = arr.set(1, 2020); // 변경전 기존에 저장되있던 값 리턴 
		System.out.println(arr);
		System.out.println(prev);
		
		arr.clear();
		System.out.println(arr);
		
		
		
		
		
		
		
	}
}
