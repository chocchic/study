package day11;

public class API04 {
	
	static String s; // 자동 초기화 null
	static String s2 =""; // ""로 초기화
	public static void main(String[] args) {
		for(int j = 0; j < 10;j++) {
			s+=j;
			s2+=j;
		}
		System.out.println(s); // null이 자동 초기화로 들어가 있는 상태
		System.out.println(s2);
		
		String java = "Java";
		String cpp = "C++";
		
		int result = java.compareTo(cpp);
		System.out.println(result);
		boolean b = java.equals(cpp);
		System.out.println(b);
		
		
		//문자열 연결
		System.out.println("abcd" + 1 + true + 2.13e-3 + 'E' + "fgh");
		System.out.println(new API04() + "입니다");
		System.out.println("I Love ".concat(cpp));
		System.out.println("I Love ".concat("chocolate"));
	
		//trim()
		String a = "		abcd	efg			";
		String newa = a.trim();
		System.out.println(newa); // 앞뒤 공백 제거
		
		//contains
		String c = new String(" c#");
		String c2= new String(", c++");
		System.out.println(c.length());
		System.out.println(c.contains("#"));
		
		c = c.concat(c2);
		System.out.println(c);
		c = c.replace("c#", "java");
		System.out.println(c);
		
		//split
		String[] str = c.split(",");
		for(String s : str) {
			System.out.println(s);
		}
		
		String str2 = c.substring(3,5); // 3번부터 5번 전까지 자르기, 5번은 포함 X
		System.out.println(str2);
		
		String[] words = {new String("aaa"),new String("bbb"), new String("ccc")};
		for(int i = 0; i< words.length; i++) {
			if(words[i].equals("ccc")) { // words[i] == ccc 쓰면 주소 비교라 값끼리 비교가 안됨
				System.out.println("ccc와 동일한 요소가 있습니다.");
			}
		}
		
		int value = 100;
		String strValue = String.valueOf(value); // String strValue = value + ""; 해도 같은 의미
		System.out.println(strValue);
		
		String fileName = "hello.test.java";
		int idx = fileName.indexOf('.');
		System.out.println(idx);
		int lastidx = fileName.lastIndexOf('.');
		System.out.println(lastidx);
		String ext = fileName.substring(idx);
		String name = fileName.substring(0,idx);
		System.out.println(name);
	}
}
