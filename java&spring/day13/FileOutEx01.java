package day13;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutEx01 {
	public static void main(String[] args) {
		byte[] b = {7,34,6,2,-1,70};
		
		try {
			// 파일 출력 스트림 생성
			FileOutputStream fout = new FileOutputStream("c:\\test\\test.out");
			// 파일 쓰기
//			// 낱개로 쓰기
//			for(int i = 0; i< b.length;i++) {
//				fout.write(b[i]);
//			}
//			// 배열 통응로 보내서 쓰기
			fout.write(b);
			
			//스트림 닫기
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 예외를 한번데 받게 Exception으로 받아도 된다
		
	}
}
