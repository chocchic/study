package day09;

import java.util.Scanner;

//인터페이스
/*
	Stack 인터페이스를 상속받아 문자열을 저장하는 StringStack 클래스를 구현하세요. 
	아래와 같은 실행결과가 나오도록 main()을 참고하여 작성하세요. 
	실행결과:
		총 스택 저장 공간의 크기 입력 >>  3 (사용자가 입력)
		문자열 입력 >> hello
		문자열 입력 >> sunny
		문자열 입력 >> smile
		문자열 입력 >> happy
		스택이 꽉 차서 푸시 불가!
		문자열 입력 >> 그만
		스택에 저장된 모든 문자열 팝 : smile sunny hello
*/
interface Stack {
	int length();					// 현재 스택에 저장된 개수 리턴
	int capacity();					// 스택의 전체 저장 가능한 개수 리턴
	String pop();					// 스택의 톱(top)에 저장된 문자열 리턴하고 저장소에서 문자열 삭제
	boolean push(String val);		// 스택의 톱(top)에 저장소에 문자열 넣고 실행 결과 boolean 타입으로 리턴
}
class StringStack implements Stack{
	private int length;
	private int size;
	private String[] s;
	Scanner sc = new Scanner(System.in);
	@Override
	public int length() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public int capacity() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public String pop() {
		length--;
		return s[length];
	}

	@Override
	public boolean push(String val) {
		if(length<size) {
			s[length] = val;
			length++;
			return true;
		}
		return false;
	}
	public void run() {
		System.out.print("총 스택 저장 공간의 크기 입력 >>  ");
		size = Integer.parseInt(sc.nextLine());
		s = new String[size];
		length = 0;
		boolean running = true;
		while(running) {
			System.out.print("문자열 입력 >> ");
			String newstring = sc.nextLine();
			if(!newstring.equals("그만")) {
				if(!push(newstring)) {
					System.out.println("문자열이 꽉차서 푸시불가");
				}
			}else {
				System.out.print("스택에 저장된 모든 문자열 팝 : ");
				while(length>0) {
				System.out.print(pop() + " ");
				}
				running=false;
			}
		}
	}
}
public class Test916 {
	public static void main(String[] args) {

		StringStack s = new StringStack();
		s.run();	// 프로그램 구동 시작
		
	}
}