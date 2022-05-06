package day07;

import java.util.Scanner;

class Book3{
	String title;
	String author;
	
	Book3(String title, String author){
		this.title = title;
		this.author = author;
	}
}
public class Test711 {
	public static void main(String[] args) {
		// Book3 클래스를 활용하여 2개짜리 Book3 객체 배열을 만들고, 사용자로부터 책의 제목과 저자를 입력받아 배열을 만들고 출력.
		Scanner sc = new Scanner(System.in);
		Book3[] bookList = new Book3[2];
		
		for(int i = 0; i<bookList.length; i++) {
			bookList[i] = new Book3(sc.nextLine(), sc.nextLine());
		}
		
		for(Book3 b: bookList) {
			System.out.println(b.title + " " + b.author);
		}
	}
}
