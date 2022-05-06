package day02;
import java.util.Scanner;
public class Test17 {
	public static void main(String[] args) {
		// 점수를 입력받고, 90점 이상이면 "수",
		// 80점 이상이면 "우", 70점 이상이면 "미",
		// 그이하는 "재시험" 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("점수를 입력하세요 : ");
		int score = sc.nextInt(); // int score = Integer.parseInt(sc.nextLine());
		//score에 int제외한 무언가가 들어왔을 때 예외 처리 필요
		if(score >= 90) {
			System.out.println("수");
		}else if(score >= 80) {
			System.out.println("우");
		}else if(score >= 70) {
			System.out.println("미");
		}else {
			System.out.println("재시험");
		}
		sc.close();
	}
}
