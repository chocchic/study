package day13;

import java.io.FileInputStream;

public class FileInEx02 {
	public static void main(String[] args) {

		FileInputStream fis = null; 
		try {
			fis = new FileInputStream("c:\\test\\fos.txt");
			
			int c; 
			while((c = fis.read()) != -1) {
				System.out.print((char)c); // 한글 2byte 깨짐 
			}
			System.out.println();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis != null)try{ fis.close();}catch(Exception e){ e.printStackTrace(); }
		}
		
		
		
		
		
		
		
	}
}
