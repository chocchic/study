package day11;

import java.util.Calendar;

public class API07 {
	public static String toString(Calendar date) {
		return date.get(Calendar.YEAR) + "년 " + (date.get(Calendar.MONTH)+1) + "월 " + date.get(Calendar.DATE) + "일 ";
	}
	public static void main(String[] args) {
		final String[] DAY_OF_WEEK = {"","일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();
		date1.set(2022,11,25);
		System.out.println(toString(date1) + DAY_OF_WEEK[date1.get(Calendar.DAY_OF_WEEK)]);
		System.out.println(toString(date2) + DAY_OF_WEEK[date2.get(Calendar.DAY_OF_WEEK)]);
	}
}
