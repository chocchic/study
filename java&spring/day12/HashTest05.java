package day12;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashTest05 {
	public static void main(String[] args) {
		HashMap map = new HashMap();
		map.put("피카츄", 100);
		map.put("꼬북이", 100);
		map.put("라이츄", 80);
		map.put("아이언", 90);
		
		Set set = map.entrySet();
//		System.out.println(set);
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("이름 : " + e.getKey() + ", 점수 : " + e.getValue());
		}
		
		System.out.println("참가자 명단 : " + map.keySet());
		
		// 총점
		int total = 0;
		Collection values = map.values();
		it = values.iterator();
		while(it.hasNext()) {
			int i = (Integer)it.next();
			total+=i;
		}
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + total/set.size());
		System.out.println("최고점 : " + Collections.max(values));
		System.out.println("최저점 : " + Collections.min(values));
	}
}
