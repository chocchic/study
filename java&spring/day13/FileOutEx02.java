package day13;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class FileOutEx02 {
	public static void main(String[] args) {
		String file, str;
		Date date = new Date();
		Scanner sc = new Scanner(System.in);
		
		str = "파일 생성시간\n\n" + date + "\n\n";
		
		System.out.print("파일 이름을 입력하세요 >>  ");
		file = sc.nextLine(); // c:\\test\\fos.txt
		System.out.print("저장할 문자열을 밉력하세요 >> ");
		str+= sc.nextLine();
		
		byte[] strByte = str.getBytes();
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(file);
			fout.write(strByte);
			System.out.println(file + "파일을 성공적으로 저장했습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fout != null) try{fout.close();}catch(Exception e ) {e.printStackTrace();} 
		}
	}
}
