package day07;

import java.util.Scanner;

/*
n명이 참가하는 끝말잇기 게임을 만드세요. 
처음단어는 "자동차"이다. n명의 참가자들은 순서대로 자신의 단어를 입력하면 된다. 
끝말잇기에서 틀리면 게임오버 -> 진사람 이름 출력하고 프로그램 종료 
WordGameApp 클래스와 각 선수를 나타내는 Player 클래스 작성. 
WordGameApp : 게임을 전체적으로 진행하는 run(), 
			run()에서 플레이어수 만큼 Player객체 배열 생성
Player : 플레이어 이름, 단어입력받는 getWordFromUser()메서드, 
 		끝말잇기 성공여부와 게임계속할지 판별하는 checkSuccess() 메서드 
# tip : 문자열중 첫번째 문자 알아내는 방법은 String클래스의 charAt(0); 사용 
String word = "자동차"; 
char firstChar = word.charAt(0);
*/
class Player {
	String name;
	String word;
	
	void getWordFromUser(String lastword) {
		Scanner sc = new Scanner(System.in);
		System.out.println("그전 단어 : " + lastword);
		System.out.println(name + "님 단어를 입력하세요 : ");
		this.word = sc.nextLine();
	}
	
	boolean checkSuccess(String lastword) {
		if(lastword.charAt(lastword.length()-1) == word.charAt(0)) {
			return true;
		}else
			return false;
	}
}
class WordGameApp {
	void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("참여할 플레이어 수를 입력하세요. : ");
		int n = Integer.parseInt(sc.nextLine());
		Player[] playerList = new Player[n];
		for(int i = 0; i < n; i++) {
			System.out.println(i+1 + "번 플레이어 이름을 입력하세요 : ");
			playerList[i] = new Player();
			playerList[i].name = sc.nextLine();
		}
		int num = 0;
		String lastword = "자동차";
		while(true){
			playerList[num].getWordFromUser(lastword);
			if(playerList[num].checkSuccess(lastword)) {
				lastword = playerList[num].word;
				num++;
				if(num > (n-1)) num = 0;
			}else {
				System.out.println(playerList[num].name + "패배!!");
				break;
			}
		}
		
	}
}
public class Test719 {
	public static void main(String[] args) {
	
		// main 작성 완료
		WordGameApp game = new WordGameApp();
		game.run(); 
	}
}