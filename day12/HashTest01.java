package day12;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashTest01 {
	public static void main(String[] args) {
		// 객체 생성
		HashMap<String, String> h = new HashMap<>();
		
		// 요소 저장
		h.put("apple","사과");
		h.put("strawberry","딸기");
		h.put("watermelon","수박");
		h.put("peach","복숭아");
		
		// 요소 꺼내기
		String v = h.get("apple");
		System.out.println(v);
		v = h.get("peach");
		System.out.println(v);
		
		// 요소 지우기
		System.out.println(h);
		h.remove("strawberry");
		System.out.println(h);
		
		// 저장된 요소 개수
		System.out.println(h.size());
		
		// 전체 요소에 접근해서 key와 value를 모두 출력
		Set<String> keys = h.keySet(); // key들만 모아서 받기
		Iterator<String> it = keys.iterator(); // 반복자 얻기
		while(it.hasNext()) { // key가 있을 때만 반복
			String key = it.next(); // key 하나만 꺼내기
			String value = h.get(key); // 꺼낸 key로 value 꺼내기
			System.out.println("{" + key + ":" + value +"}");
		}
	}
}
