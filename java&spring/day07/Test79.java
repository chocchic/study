package day07;
class Book2{
	String title;
	String author;
	
	Book2(){
		System.out.println("생성자 호출");
	}
	Book2(String title){
//		this.title = title;
//		author = "작자 미상";
		// #3.
		// 항상 생성자 안에서 첫번째 명령이어야함
		this(title, "작자미상");// 매개변수 두개 있는 생성자 호출
		// #5.
		System.out.println("생성자2"); // this가 처음에 와야 오류안남
	}
	Book2(String title, String author){
		// #4.
		System.out.println("생성자3");
		this.title = title;
		this.author = author;
	}
	void show() {
		System.out.println(title +" " +author);
	}
}
public class Test79 {
	public static void main(String[] args) {
		// #1.
		Book2 little = new Book2("어린왕자","생텍쥐베리");
		Book2 kong = new Book2("콩쥐팥쥐");
		little.show();
		kong.show();
	}
}
