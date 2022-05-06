package day12;

import java.util.Iterator;
import java.util.Vector;

public class VecTest04 {
	public static void main(String[] args) {
		Vector<Integer> v = new Vector<>();
		
		v.add(10);
		v.add(20);
		v.add(30);
		
		// iterator는 컬렉션 객체에서 iterator()메서드를 통해 얻을 수 있다.
		Iterator<Integer> it = v.iterator();
		while(it.hasNext()) {
			int n = it.next();
			System.out.println(n);
		}
		
		// 다시 반복자 사용하고 싶으면 새로 담기.
		it = v.iterator();
		int total = 0;
		while(it.hasNext()) {
			System.out.println("hello");
			int nn = it.next();
			total+=nn;
		}
		System.out.println(total);
	}
}
