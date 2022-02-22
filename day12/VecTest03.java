package day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VecTest03 {
	public static void main(String[] args) {
		Vector<String> v = new Vector<String>(); // 10개 생성 -> index : 0~9
		v.add("hello"); // index = 0만 가능. 순차적으로 저장
		
		System.out.println(v);
		
		// 제네릭 타입을 생략하면 Object로 처리. 모든 타입을 섞어서 저장 가능
		// 하지만 꺼냈을 때도 Object로 돌려주므로 내용물을 온전히 잘 사용하려면, 맞는 타입으로 형변환해 담아서 사용해야 한다
		Vector v2 = new Vector();
		v2.add(10);
		v2.add("hello");
		v2.add(3.14);
		v2.add(new Point(1,2));
		System.out.println(v2);
		System.out.println(v2.get(0));
		Object o = v2.get(1);
		String str = (String)o;
		System.out.println(str.length());
		
		Object o2 = v2.get(3);
		System.out.println(o2);
		if(o2 instanceof Point) {
			Point p = (Point)o2;
			System.out.println(p.getX());
			System.out.println(p.getY());
		}
		// 다형성으로 느슨하게 받아줄 수 있다.
		List list = new ArrayList<>();
		list = new Vector<>();
	}
}
