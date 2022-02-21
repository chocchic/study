package day11;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class API10 {
	public static void main(String[] args) {
		Date day = new Date();
		System.out.println(day);
		System.out.println(day.getMonth()); // 0~11월
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		System.out.println(sdf.format(day));
		
		// 캘린더 -> sdf 활용해서
		Calendar c = Calendar.getInstance();
		System.out.println(sdf.format(c.getTime()));
	}
}
