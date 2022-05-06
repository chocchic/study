package day11;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Api10 {
	public static void main(String[] args) {

		Date day = new Date(); 
		System.out.println(day); 
		System.out.println(day.getMonth()); // 0 ~ 11월 
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		System.out.println(sdf1.format(day));
		
		// 캘린더 -> sdf 활용해서 
		Calendar c = Calendar.getInstance();
		System.out.println(sdf1.format(c.getTime()));
		
		
		
		
	}
}
