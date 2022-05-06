package day06;

/*
	char[] 배열을 전달받아 출력하는 printCharArray() 메서드와 배열 속의 ' ' 공백문자를, ','쉼표로 대치하는 replaceSpace() 메서드를 작성해보세요.
	(편의를 위해 Test68 클래스 안에 static 메서드로 작성해보세요.)
*/
public class Test68 {
	static void printCharArray(char[] ch) {
		for(int i = 0; i< ch.length; i++) {
			System.out.print(ch[i]);
		}
		System.out.println();
	}
	
	static void replaceSpace(char[] ch) {
		for(int i = 0; i< ch.length; i++) {
			if(ch[i] == ' ') ch[i] = ',';
		}
	}
	public static void main(String[] args) {
		char[] c = {'I',' ', 'a', 'm', ' ', 'a',' ', 'b', 'o', 'y'};
		printCharArray(c);
		replaceSpace(c);
		printCharArray(c);
		
	}
}
