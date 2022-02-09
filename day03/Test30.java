package day03;

public class Test30 {
	public static void main(String[] args) {
		//main메서드의 args배열은 
		//String[] args;만 되어있는 것과 같음
		/*
		args = new String[5]; // 생성해줘야함		
		args[0] = "abc";
		System.out.println(args[0]);
		*/
		//코드에 직접 사용하기 위한 용도는 아님.
		//프로그램이 시작할 때 어떤 데이터를 받으면서 시작할 때, args배열을 초기화해주는 것처럼 데이터를 받음.
		//던져줄 떄를 대비해 만들어놨다.
		
		System.out.println(args[0]);
		System.out.println(args[1]);
//		System.out.println(args[2]); // ArrayIndexOutofBoundException, 없는 인덱스임.
		//그냥 실행할 떄는 인덱스 outofbound오류
		//상단 Run 탭의 Run configuration에서 현재 파일의 Arguments에 들어가 10과 20을 추가하고 실행하면 출력된다
		//출력: 
		//10
		//20
				
	}
}
