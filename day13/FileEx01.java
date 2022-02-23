package day13;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileEx01 {
	public static void main(String[] args) {
		// 경로명 적을 떄 역슬래쉬 두개(이스케이프 문자로 인식하지 않게)
		
		File f = new File("C:\\Windows\\system.ini");
		
		long size = f.length(); // 파일 크기
		System.out.println(size);
		
		String filename = f.getName(); // 파일명
		System.out.println(filename);
		
		String path = f.getPath(); // 파일 경로
		System.out.println(path);
		
		String parentPath = f.getParent(); // 파일의 부모 경로
		System.out.println(parentPath);
		
		String absolPath = f.getAbsolutePath(); // 절대 경로
		System.out.println(absolPath);
		
		if(f.isFile()) { System.out.println(f.getPath() + "는 파일입니다."); }
		else if(f.isDirectory()) {System.out.println(f.getPath() + "는 폴더입니다."); }
		
		System.out.println(f.isHidden());
		System.out.println(f.canRead());
		System.out.println(f.canWrite());
		System.out.println(new Date(f.lastModified()));
		
		File f2 = new File("c:\\Windows");
		File[] list = f2.listFiles();
		for(int i= 0; i<list.length;i++) {
			System.out.print(list[i].getName());
			System.out.println("\t\t파일크기: "+ list[i].length()); // 파일 크기가 0이면 대부분이 폴더
		}
		
		File f3 = new File("C:\\test\\kor.txt");
		try {
			f3.createNewFile(); // 파일 생성
		} catch (IOException e) {
			System.out.println(e);
		}
		
		File f4 = new File("C:\\test\\iotest");
		if(!f4.exists()) {
			f4.mkdir();
		}
	}
}
