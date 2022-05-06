package day12;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
	HashMap을 이용하여 학생이름과 자바 점수를 기록하는 관리 프로그램을 만드세요. 
	아래 프로그램 메뉴에서 각 번호로 서비스를 선택하고, 6번을 입력받으면 프로그램 종료. 
	*** 자바 성적 관리 프로그램 ***
	1. 전체 조회
	2. 등록
	3. 수정
	4. 삭제
	5. 전체 평균
	6. 프로그램 종료 
	
*/
public class CollEx04 {
	public static HashMap<String, Integer> revise(HashMap<String, Integer> g) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("무엇을 수정하시겠습니까? 1. 점수 2. 이름");
		int r = Integer.parseInt(sc.nextLine());
		if(r == 1) {
			System.out.print("점수를 수정할 학생 이름 입력 >> ");
			String name = sc.nextLine();
			if(g.containsKey(name)) {
				System.out.print("점수 입력 >> ");
				g.replace(name,Integer.parseInt(sc.nextLine()));
			}else {
				System.out.println("없는 사용자 입니다.");
			}
		}else if(r == 2){
			System.out.print("이름을 수정할 학생 이름 입력 >> ");
			String name = sc.nextLine();
			if(g.containsKey(name)) {
				System.out.print("새 이름 입력 >> ");
				int score = g.get(name);
				g.remove(name);
				g.put(sc.nextLine(), score);
			}else {
				System.out.println("없는 사용자 입니다.");
			}
		}
		
		return g;
	}
	
	public static void main(String[] args) {
		HashMap<String, Integer> grade = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("*** 자바 성적 관리 프로그램 ***\n1. 전체 조회 2. 등록 3. 수정 	4. 삭제 5. 전체 평균 6. 프로그램 종료\n입력 >> ");
			int option = Integer.parseInt(sc.nextLine());
			if(option == 1) {
				System.out.println(grade);
			}else if(option == 2) {
				try {
				System.out.print("등록할 학생의 이름을 입력하세요 >>  "); 
				String name = sc.nextLine();
				if(!grade.containsKey(name)) {
					System.out.print("학생의 점수를 입력하세요 >>  ");
					grade.put(name, Integer.parseInt(sc.nextLine()));
					System.out.println("등록 완료");
				}else {
					System.out.println("이미 있는 이름입니다.");
				}
				}catch(Exception e) {
					System.out.println("등록 실패");
				}
			}else if(option == 3) {
				try {
					grade = revise(grade);
				}catch (Exception e) {
					System.out.println("수정 실패");
				}
			}else if(option == 4) {
				System.out.print("삭제할 학생의 이름 입력 >> ");
				try {
					String name = sc.nextLine();
					if(grade.containsKey(name)) grade.remove(name);
					else System.out.println("없는 사용자입니다.");
				}catch(Exception e) {
					System.out.println("삭제 실패");
				}
			}else if(option == 5) {
				int total = 0;
				Collection values = grade.values();
				Iterator it = values.iterator();
				while(it.hasNext()) {
					int i = (Integer)it.next();
					total+=i;
				}
				System.out.println("평균 : " + total / grade.size());
			}else if(option == 6) {
				System.out.println("프로그램 종료");
				break;
			}else {
				System.out.println("잘못된 입력값입니다.");
			}
		}
	}
}
