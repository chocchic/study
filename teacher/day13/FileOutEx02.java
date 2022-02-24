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
		
		System.out.println("파일 이름을 입력하세요>>");
		file = sc.nextLine(); 		// c:\\test\\fos.txt
		System.out.println("저장할 문자열을 입력하세요>>");
		str += sc.nextLine(); 
		
		byte[] strByte = str.getBytes();  // 바이트단위로 전송하니 문자열을 byte로 변경 
		
		FileOutputStream fout = null; 
		
		try {
			fout = new FileOutputStream(file);
			fout.write(strByte);
			
			//fout.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {			
			if(fout != null)try { fout.close(); }catch(Exception e) { e.printStackTrace();} 
			if(sc != null)try { sc.close(); }catch(Exception e) { e.printStackTrace();} 
		}
		
		System.out.println(file + " 파일을 성공적으로 저장했습니다.");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
