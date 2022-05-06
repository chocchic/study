package day13;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInEx02 {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:\\test\\fos.txt");
			int c;
			while((c=fis.read())!=-1) {
				System.out.print((char)c); // 한글 2byte라서 깨짐
			}
			System.out.println();	
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
