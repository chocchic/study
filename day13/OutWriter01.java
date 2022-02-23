package day13;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class OutWriter01 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		OutputStreamWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		String str = "파일 생성 시간 : " + new java.util.Date() + "\n\n";
		System.out.print("메세지 입력 >> ");
		str += sc.nextLine();
		try {
			fos=new FileOutputStream("c:\\test\\outStrWrt.txt");
			out = new OutputStreamWriter(fos);
			
			System.out.println("Encoding : " + out.getEncoding());
			out.write(str);
			out.flush(); // 닫기 전에 스트림 비우기
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(fos !=null) try {fos.close();}catch(Exception e) {e.printStackTrace();}
			if(out !=null) try {out.close();}catch(Exception e) {e.printStackTrace();}
			if(sc !=null) try {sc.close();}catch(Exception e) {e.printStackTrace();}
		}
	}
}
