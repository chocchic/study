package day03;

public class Test25 {
	public static void main(String[] args) {

		/* 잘사용하지 않음 
		int a;
		for(a = 0; a < 10; a++) {
			System.out.println(a);
		}
		System.out.println(a);
		*/
		
		/* 초기식 생략하는 방법 (잘 사용X) 
		int a = 0; 
		for( ; a < 10; a++) {
			System.out.println(a);
		}
		
		for(int i = 0; i < 3; i++) {
			System.out.println("hello");
		}
		*/
		
		//for( ; ; ) {
			//System.out.println("hahahahahahahah");
//			if (조건) {
//				break;
//			}
		//}
		
		for(int i = 0; i < 10; i++) {
			if(i == 5) {
				continue;
			}
			System.out.println(i);
		}
				
		
		
		
		
		
		
		
		
		
	}
}
