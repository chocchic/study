package day13;

import java.io.FileInputStream;

public class FileInEx01 {
	public static void main(String[] args) {

		byte [] b = new byte[6]; 
		
		FileInputStream fin = null; 
		
		try {
			//byte[] b = {7, 34, 6, 2, -1, 70}; 
			fin = new FileInputStream("c:\\test\\test.out"); 
			
//			int i = 0; // 배열 idx로 사용할 변수 
//			int c; // 읽은 파일 임시저장 변수 
//			while((c = fin.read()) != -1) {
//				b[i] = (byte)c; 
//				i++; 
//			}
			// 배열과 데이터 크기맞고, 준비해둔 배열을 던져주면 알아서 읽어서 체워준다. 
			fin.read(b); 
			
			// 확인 출력 
			for(byte bb : b) {
				System.out.print(bb + "  ");
			}
			System.out.println();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fin != null) try {fin.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		
		
		
		
		
		
		
	}
}
