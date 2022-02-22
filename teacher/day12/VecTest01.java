package day12;

import java.util.Vector;

public class VecTest01 {
	public static void main(String[] args) {

		Vector<Integer> v = new Vector<Integer>();
		
		// 저장 
		v.add(10); 
		v.add(20); 
		v.add(30); 
		v.add(null);
		v.add(2, 100);
		
		System.out.println(v);
		
		// 꺼내기 
		System.out.println(v.get(0));
		System.out.println(v.elementAt(0));
		
		System.out.println(v.size());	  // 현재 저장된 요소의 개수  
		System.out.println(v.capacity()); // 저장할 수 있는 공간의 크기 10 
		
		// 삭제 
		v.remove(1);
		System.out.println(v);
		
		v.clear();
		System.out.println(v);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
