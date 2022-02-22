package day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class VecTest03 {
	public static void main(String[] args) {

		// 제네릭 타입을 생략하면 Object로 처리 
		// -> 모든 타입을 섞어서 저장 가능 
		// 그러나, 꺼냈을때도 Object로 돌려주므로 
		// 내용물을 온전히 잘 사용하려면, 맞는 타입으로 형변환해 담아서 사용해야함.
		Vector v = new Vector(); 
		v.add(10);
		v.add("hello");
		v.add(3.14);
		v.add(new Point(1,2)); 
		System.out.println(v);
		
		Object o = v.get(1);
		String str = (String)o; // instanceof 생략함 
		System.out.println(str.length());
		
		Object o2 = v.get(3);
		System.out.println(o2);
		if(o2 instanceof Point) {
			Point p = (Point)o2; 
			System.out.println(p.getX());
			System.out.println(p.getY());
		}
		
		
		List list = new ArrayList(); 
		list = new Vector(); 
		list = new Stack();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
