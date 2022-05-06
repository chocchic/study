package day13;

import java.io.File;

public class FileEx01 {
	public static void main(String[] args) {
		// 경로명 적을때 역슬래쉬 두개 (이스케이프 문자로 인식하지 않게) 
		// "Users/java/day01/test.txt"
		
		//File f = new File("C:\\Windows\\system.ini"); 
		File f = new File("c:\\Windows\\system.ini"); 
		long size = f.length();  // 파일 크기 
		System.out.println(size);
		
		String filename = f.getName(); // 파일명 
		System.out.println(filename);
		
		String path = f.getPath(); // 파일 경로 
		System.out.println(path);
		
		String parentPath = f.getParent(); // 파일의 부모 경로 
		System.out.println(parentPath);
		
		String absolPath = f.getAbsolutePath(); // 절대경로 
		System.out.println(absolPath);
		
		if(f.isFile()) { System.out.println(f.getPath() + "는 파일입니다."); }
		else if(f.isDirectory()) { System.out.println(f.getPath() + "는 폴더입니다."); }
		
		System.out.println(f.isHidden()); 
		System.out.println(f.canRead());
		System.out.println(f.canWrite());
		System.out.println(new java.util.Date(f.lastModified()));
		
		File f2 = new File("c:\\Windows"); 
		File[] list = f2.listFiles(); 
		for(int i = 0; i < list.length; i++) {
			System.out.print(list[i].getName());
			System.out.println("\t파일크기: " + list[i].length());
		}
		
		File f3 = new File("c:\\test\\kor.txt"); 
		try {
			f3.createNewFile();  // 파일 만들기 
		}catch(Exception e) {
			System.out.println(e);
		}
		
		File f4 = new File("c:\\test\\iotest"); 
		if(!f4.exists()) {
			System.out.println("없으니 생성");
			f4.mkdir();
		}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
