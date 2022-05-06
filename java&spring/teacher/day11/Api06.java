package day11;

import java.util.Calendar;

public class Api06 {
	public static void main(String[] args) {

		Calendar now = Calendar.getInstance(); 	//new Calendar(); (x)
		System.out.println(now);
		
		// 년도와 월 알아보기 
		int month = now.get(Calendar.MONTH); 
		int year = now.get(Calendar.YEAR); 
		System.out.println(year + "년, " + (month+1) + "월");
		
		
		
		
		
	}
}
