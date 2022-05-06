package day13;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReadEx01 {
	public static void main(String[] args) {
		// InputStreamReader : 바이트 스트림 -> 문자 스트림
		FileInputStream fin = null;
		InputStreamReader in = null;
		
		try {
			fin = new FileInputStream("C:\\test\\fos.txt");
			in = new InputStreamReader(fin); // workspace가 UTF-8로 해줌
			
			System.out.println("encoding: "+ in.getEncoding());
			int c;
			while((c = in.read()) != -1) {
				System.out.print((char)c);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fin!=null) {
				try {
					fin.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
