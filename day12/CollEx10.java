package day12;

import java.util.ArrayList;
import java.util.Scanner;

/*
	나라와 수도 맞추기 게임을 만들어 보세요. 
	나라 이름(country)과 수도(capital)로 구성된 Nation 클래스를 작성하고,
	ArrayList를 이용하여 아래 실행 예시와 같이 작동되도록 프로그램을 작성하세요.
	실행예시:
		** 수도 맞추기 게임을 시작합니다 **
		입력:1, 퀴즈:2, 종료:3 >> 1 (사용자 입력) 
		현재 8개의 나라와 수도가 입력되어 있습니다. 
		나라와 수도 입력 9>> 한국 서울   (사용자 입력)
		나라와 수도 입력 10>> 그리스 아테네 
		그리스는 이미 있습니다..
		나라와 수도 입력 10>> 호주 시드니
		나라와 수도 입력 11>> 이탈리아 로마
		나라와 수도 입력 12>> 그만 
		입력:1, 퀴즈:2, 종료:3 >> 2 
		호주의 수도는?  시드니  (사용자 입력) 
		정답!! 
		독일의 수도는?  하얼빈
		아닙니다!!
		멕시코의 수도는?  하얼빈 
		아닙니다!!
		이탈리아의 수도는?  로마
		정답!!
		영국의 수도는?  그만 
		입력:1, 퀴즈:2, 종료:3 >> 3
		프로그램을 종료합니다.
*/
class Nation{
	private String country, capital;
	
	public Nation(String country, String capital) {
		this.country = country; this.capital = capital;
	}
	public String getCountry() {return country;}
	public String getCapital() {
		return capital;
	}
}
class CapitalGame{
	ArrayList<Nation> ctoc;
	Scanner sc;
	public CapitalGame() {
		ctoc = new ArrayList<>();
		ctoc.add(new Nation("미국","워싱턴D.C."));
		ctoc.add(new Nation("독일","베를린"));
		ctoc.add(new Nation("중국","베이징"));
		ctoc.add(new Nation("일본","도쿄"));
		ctoc.add(new Nation("멕시코","멕시코시티"));
		ctoc.add(new Nation("프랑스","파리"));
		ctoc.add(new Nation("영국","런던"));
		ctoc.add(new Nation("그리스","아테네"));
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
			boolean contains = false;
			for(Nation n : ctoc) {
				if(n.getCountry().equals(ss[0]))
					contains = true;
			}
			if(contains){
				System.out.println(ss[0] + "은 이미 있습니다.");
			}else {
				ctoc.add(new Nation(ss[0], ss[1]));
			}
		}
	}
	
	void quiz(){
		while(true) {
			int num = (int)(Math.random()*ctoc.size());
			System.out.print(ctoc.get(num).getCountry()+"의 수도는? ");
			String answer = sc.nextLine();
			if(answer.equals("그만")) break;
			if(ctoc.get(num).getCapital().equals(answer)){
				System.out.println("정답!!");
			}else {
				System.out.println("아닙니다!!");
			}
		}
	}
	
}
public class CollEx10 {
	public static void main(String[] args) {

		// main 작성 완료, 주석 해제하고 실행 
		CapitalGame game = new CapitalGame(); 
		game.run(); 
		
		
	}
}
