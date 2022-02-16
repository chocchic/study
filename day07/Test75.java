package day07;

class Book{
	String title;  // 제목
	String author; // 작자
	// 매개변수가 있는 생성자가 작성되면, 매개변수가 없이는 객체 생성 안됨.
	Book(String t){ // 책 제목을 외부로부터 입력받아 책 생산이 되도록 생성자 만듬
		title = t;
		author = "작자미상";
	}
	Book(String t, String a){
		title = t;
		author = a;
	}
}

public class Test75 {
	public static void main(String[] args) {
		Book kong = new Book("콩쥐팥쥐");
		System.out.println(kong.title + " " + kong.author);
		Book littlePrince = new Book("어린왕자","생텍쥐베리");
		System.out.println(littlePrince.title+" " + littlePrince.author);
	}
}
