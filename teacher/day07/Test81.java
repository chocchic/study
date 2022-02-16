package day07;

import java.util.Scanner;

class Book3 {
	String title;
	String author;
	Book3(String title, String author){
		this.title = title;
		this.author = author;
	}
}
public class Test81 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Book3 클래스를 활용하여 2개짜리 Book3 객체 배열을 만들고, 
		// 사용자로부터 책의 제목과 저자를 입력받아 배열을 완성하고 출력해보세요. 
		Book3 [] books = new Book3[2]; 
		for(int i = 0; i < books.length; i++) {
			System.out.println("제목>>");
			String title = sc.nextLine(); 
			System.out.println("저자>>");
			String author = sc.nextLine(); 
			books[i] = new Book3(title, author);
		}
		
		for(int i = 0; i < books.length; i++) {
			System.out.println(books[i].title + ", " + books[i].author);
		}
		
		for(Book3 b : books) {
			System.out.println(b.title + ", " + b.author);
		}
		
		
		
		sc.close();
		
	}
}





