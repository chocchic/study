package day11;

import java.util.Calendar;

public class Api08 {
	public static void main(String[] args) {

		Calendar time1 = Calendar.getInstance();
		Calendar time2 = Calendar.getInstance();
		
		time1.set(Calendar.HOUR_OF_DAY, 10);
		time1.set(Calendar.MINUTE, 20);
		time1.set(Calendar.SECOND, 30);
		
		time2.set(Calendar.HOUR_OF_DAY, 20);
		time2.set(Calendar.MINUTE, 30);
		time2.set(Calendar.SECOND, 10);
		
		System.out.println("time1 : " + time1.get(Calendar.HOUR_OF_DAY)+"시 " + time1.get(Calendar.MINUTE) + "분 " + time1.get(Calendar.SECOND) + "초");
		System.out.println("time2 : " + time2.get(Calendar.HOUR_OF_DAY)+"시 " + time2.get(Calendar.MINUTE) + "분 " + time2.get(Calendar.SECOND) + "초");
		
		long diff = Math.abs(time2.getTimeInMillis() - time1.getTimeInMillis())/1000;
		System.out.println(diff);
		
		
		final int[] TIME_UNIT = {3600, 60, 1}; 
		final String[] TIME_UNIT_NAME = {"시간 " ,"분 ", "초"}; 
		
		String tmp = ""; 
		for(int i = 0; i < TIME_UNIT.length; i++) {
			tmp += diff/TIME_UNIT[i] + TIME_UNIT_NAME[i]; 
			diff %= TIME_UNIT[i]; 
		}
		System.out.println("시분초로 변환 : " + tmp);
		
		
		
		
		
		
	}
}
