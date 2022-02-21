package day11;

import java.util.Calendar;

public class API06 {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal);
		
		//년도와 월 알아보기
		int month = cal.get(Calendar.MONTH) +1;
		int year = cal.get(Calendar.YEAR);
		System.out.println(year + "년, " + month + "월");
		
	}
}
