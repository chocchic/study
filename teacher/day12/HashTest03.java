package day12;

import java.util.HashMap;
import java.util.Scanner;

public class HashTest03 {
	public static void main(String[] args) {

		HashMap map = new HashMap(); 
		map.put("java", "1234");
		map.put("fdfd", "1234");
		map.put("qwer", "1234");
		map.put("rere", "1234");
		//map.put(100, "1234");
		System.out.println(map);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("id와 pw를 입력하세요.");
		System.out.println("id >> ");
		String id = sc.nextLine(); 
		System.out.println("pw >> ");
		String pw = sc.nextLine(); 
		if(!map.containsKey(id)) {
			System.out.println("해당 id는 존재하지 않습니다.");
		}else {
			if(!map.get(id).equals(pw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}else {
				System.out.println("로그인 성공! " + id + "님 환영합니다.");
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
