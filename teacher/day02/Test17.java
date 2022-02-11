package day02;
import java.util.Scanner;
public class Test17 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 점수를 입력받고, 90이상이면 "수", 
		//	80점 이상이면 "우", 70점 이상이면 "미", 
		//	그 이하는 "재시험" 출력 
		System.out.println("점수입력:");
		int score = Integer.parseInt(sc.nextLine());
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
