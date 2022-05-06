package day11;

public class Api04 {
	
	static String s; 		// 자동초기화 null
	static String s2 = "";  // ""   
	
	public static void main(String[] args) {
		
		String fileName = "hello.test.java"; 
		int idx = fileName.lastIndexOf('.'); 
		String ext = fileName.substring(idx); 
		String name = fileName.substring(0, idx);
		System.out.println(name);

		
		/*
		int value = 100; 
		String strValue = String.valueOf(value);
		System.out.println(strValue);
		strValue = value + "";
		
		char[] c = {'a', 'b', 'c'}; 
		String str = new String(c); 
		System.out.println(str);
		
		for(int i = 0; i < 10; i++) {
			s += i;   // null + 0
			s2 += i; 
		}
		System.out.println(s);
		System.out.println(s2);
		
		
		String java = "Java"; 
		String cpp = "C++"; 
		int result = java.compareTo(cpp);
		System.out.println(result);
		boolean b = java.equals(cpp);
		System.out.println(b);
		
		
		// 문자열 연결 
		System.out.println("abcd" + 1 + true + 2.13e-3 + 'E' + "fgh"); 
		System.out.println(new Api04() + "입니다.");
		System.out.println("I love ".concat("chocolate"));
		
		// trim() 
		String a = "        abcd    efg     ".trim(); 
		System.out.println(a);
		
		
		String a = new String(" c#"); 
		String b = new String(", c++"); 
		System.out.println(a.length());
		System.out.println(a.contains("#"));
		a = a.concat(b);
		System.out.println(a);
		a = a.replace("c#", "java"); 
		System.out.println(a); // java, c++
		
		String[] str = a.split(","); 
		for(int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
		
		String str2 = a.substring(3, 5);  // 5전까지, 5번은 포함안함
		System.out.println(str2);
		
		
		String[] words = {new String("aaa"), new String("bbb"), new String("ccc")};
					
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals("ccc")) {
				System.out.println("ccc와 동일한 요소가 있습니다.");
			}
		}
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
