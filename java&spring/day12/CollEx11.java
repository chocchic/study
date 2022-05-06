package day12;
/*
	CollEx10번 문제를 HashMap을 이용하여 작성하세요. 
	key는 나라이름, value는 수도입니다. 
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class CapitalGame2{
	HashMap<String, String> ctoc;
	HashMap<Integer,String> ctocIndex;
	Scanner sc;
	public CapitalGame2() {
		ctoc = new HashMap<>();
		ctocIndex = new HashMap<>();
		ctoc.put("미국","워싱턴D.C.");
		ctocIndex.put(0, "미국");
		ctoc.put("독일","베를린");
		ctocIndex.put(1, "독일");
		ctoc.put("중국","베이징");
		ctocIndex.put(2, "중국");
		ctoc.put("일본","도쿄");
		ctocIndex.put(3, "일본");
		ctoc.put("멕시코","멕시코시티");
		ctocIndex.put(4, "멕시코");
		ctoc.put("프랑스","파리");
		ctocIndex.put(5, "프랑스");
		ctoc.put("영국","런던");
		ctocIndex.put(6, "영국");
		ctoc.put("그리스","아테네");
		ctocIndex.put(7, "그리스");
		sc = new Scanner(System.in);
	}
	
	public void run() {
		System.out.println("** 수도 맞추기 게임을 시작합니다 **");
		while(true) {
			System.out.print("입력:1, 퀴즈:2, 종료:3 >> ");
			int op = Integer.parseInt(sc.nextLine());
			if(op == 1) {
				insert();
			}else if(op ==2) {
				quiz();
			}else if(op == 3) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}else {
				System.out.println("올바른 값을 입력하세요");
			}
		}
	}
	
	void insert() {
		System.out.println("현재 "+ctoc.size()+"개의 나라와 수도가 입력되어 있습니다. ");
		while(true) {
			System.out.print("나라와 수도 입력 "+(ctoc.size()+1)+">> ");
			String s = sc.nextLine();
			if(s.equals("그만")) break;
			String[] ss = s.split(" ");
			if(ctoc.containsKey(ss[0])) {
				System.out.println(ss[0] + "은 이미 있습니다.");
			}else {
				ctoc.put(ss[0], ss[1]);
				ctocIndex.put(ctoc.size()+1, ss[0]);
			}
		}
	}
	
	void quiz(){
		while(true) {
			int num = (int)(Math.random()*ctoc.size());
			String q_country = ctocIndex.get(num);
			System.out.print(q_country+"의 수도는? ");
			String answer = sc.nextLine();
			if(answer.equals("그만")) break;
			if(ctoc.get(q_country).equals(answer)){
				System.out.println("정답!!");
			}else {
				System.out.println("아닙니다!!");
			}
		}
	}
	
}

public class CollEx11 {
	public static void main(String[] args) {
		
		
		CapitalGame2 game = new CapitalGame2(); 
		game.run(); 
		
	}
}
