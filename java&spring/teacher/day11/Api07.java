package day11;

import java.util.Calendar;

public class Api07 {
	
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " 
				+ date.get(Calendar.DATE) + "일 ";
	}
	
	public static void main(String[] args) {

		final String [] DAY_OF_WEEK = {"","일","월","화","수","목","금","토"}; 
		
		
		
		Calendar date1 = Calendar.getInstance(); 
		Calendar date2 = Calendar.getInstance(); 
		date1.set(2022, 11, 25);
		
		System.out.println(toString(date1) + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)] + "요일");
		System.out.println(toString(date2) + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)] + "요일");
		
		
		
		
	}
}
