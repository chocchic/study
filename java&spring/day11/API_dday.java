package day11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class API_dday {
	public static void main(String[] args) {
		
		//D-day 계산기 만들어보기
		Scanner sc = new Scanner(System.in);
		Calendar today = Calendar.getInstance();
		today.setTime(new Date()); //오늘 날짜를 Calendar객체에 저장
		String dataPattern = "yyyyMMdd"; // 이 표현식 틀리면 완전 오류남 주의!!
		System.out.print("d-day 날을 입력하세요(" + dataPattern+ ") : ");
		String d = sc.nextLine();
		try {
			Calendar dday = Calendar.getInstance();
			dday.setTime(new SimpleDateFormat(dataPattern).parse(d)); //today라는 Calendar객체와 날짜 비교를 위해 d-day를 Calendar객체에 저장
			long todayMill = today.getTimeInMillis();
			long ddayMill = dday.getTimeInMillis();
			if(todayMill < ddayMill) {
				// d-day가 아직 안왔으므로 - 일자로 표시
				long leftDays = (dday.getTimeInMillis() - today.getTimeInMillis()) / 1000 / (24*60*60) + 1;
				// 이 조건문은 무조건 다음날 시작인데 그 값이 1로 시작하게끔 +1
				
				
				System.out.println("D - " + leftDays);
			}else{
				// d-day가 이미 지나갔으므로 + 알자로 표시
				long leftDays = (today.getTimeInMillis() - dday.getTimeInMillis()) / 1000 / (24*60*60); // 오늘에서 d-day 빼기 
				System.out.println("D + " + leftDays);
			}
		} catch (ParseException e) {
			System.out.println("올바르지 않은 형식입니다.");
		}
		// 시간까지 입력한다면 당일 그 일정이 끝나고 나서 +인지-인지 정확히 표현할수 있긴 하겠다.
		
	}
}
