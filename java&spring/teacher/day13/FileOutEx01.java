package day13;

import java.io.FileOutputStream;

public class FileOutEx01 {
	public static void main(String[] args) {

		byte[] b = {7, 34, 6, 2, -1, 70}; 
		
		try {
			// 파일 출력 스트림 생성 
			FileOutputStream fout = new FileOutputStream("c:\\test\\test.out"); 
			// 파일 쓰기 
			/* 낱개로 쓰기 
			for(int i = 0; i < b.length; i++) {
				fout.write(b[i]);
			}*/
			// 배열 통으로 보내서 쓰기 
			fout.write(b);
			
			// 스트림 닫기 
			fout.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
}
