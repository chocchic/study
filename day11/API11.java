package day11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class API11 {
	public static void main(String[] args) {
		// 문자열 날짜 -> Date 객체로 변환
		// 실제 데이터의 작성 패턴을 SDF으로 미리 만들어주기
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat sdf2= new SimpleDateFormat("yyyy/MM/dd");
		try {
			//변환
//			Date d = sdf.parse("2022년 1월 31일");
			Date d = sdf2.parse("2022/01/31"); // 문자열 형태의 날짜 정보를 Date 객체로 변환
			System.out.println(d);
			System.out.println(d.getMonth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
