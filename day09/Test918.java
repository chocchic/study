package day09;

import java.util.Scanner;

//추상클래스
/*
	텍스트로 입출력하는 간단한 그래픽 편집기 만들기. 
	아래 추상클래스 ShapeAbst를 상속받은 Line, Rect, Circle 클래스를 만들고, 
	실행 예시처럼 "삽입", "삭제", "모두보기", "종료"의 4가지 그래픽 편집 기능을 가진 
	클래스 GraphicEditor를 작성하세요.
	실행예시 : 
		그래픽 에디터를 실행합니다. 원하는 기능의 번호를 눌러주세요.
		1.삽입  2.삭제  3.모두보기  4.종료 >>  1 (사용자 입력)
		1.Line  2.Rect  3.Circle >>  2
		1.삽입  2.삭제  3.모두보기  4.종료 >>  1
		1.Line  2.Rect  3.Circle >>  3
		1.삽입  2.삭제  3.모두보기  4.종료 >>  3
		Rect
		Circle
		1.삽입  2.삭제  3.모두보기  4.종료 >>  2
		삭제할 도형의 위치 >> 3
		삭제할 수 없습니다.
		1.삽입  2.삭제  3.모두보기  4.종료 >>  4
		에디터를 종료합니다.
*/
abstract class ShapeAbst {
	private ShapeAbst next;
	public ShapeAbst() { next = null; }
	public void setNext(ShapeAbst obj) { next = obj; }	// 링크 연결
	public ShapeAbst getNext() { return next; }
	abstract public void draw();						// 추상메서드 : 도형이름 출력하는 기능을 갖고 있다.
}
class Line2 extends ShapeAbst{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Line");
	}
	
}
class Rect2 extends ShapeAbst{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Rect");
	}
	
}

class Circle2 extends ShapeAbst{

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Circle");
	}
	
}

class GraphEditor{
	ShapeAbst tmp, last,start = null;
	int length = 0;
	Scanner sc = new Scanner(System.in);
	
	GraphEditor(){
		System.out.println("그래픽 에디터를 실행합니다. 원하는 기능의 번호를 눌러주세요.");
		boolean running = true;
		while(running) {
			System.out.print("1.삽입  2.삭제  3.모두보기  4.종료 >>  ");
			int option = Integer.parseInt(sc.nextLine());
			if(option == 1) {
				insert();
			}else if(option == 2) {
				delete();
			}else if(option == 3) { 
				show();
			}else if(option == 4) {
				running = finish();
			}else {
				System.out.println("올바른 값을 입력해주세요");
			}
		}
	}
	boolean finish() {
		System.out.println("에디터를 종료합니다.");
		return false;
	}
	
	void show() {
		ShapeAbst tmp = start;
		System.out.println("총 길이 : " + length);
		for(int i  = 0; i <length; i++) {
			tmp.draw();
			tmp = tmp.getNext();
		}
	}
	
	void delete() {
		System.out.println("삭제할 도형의 위치 >> ");
		int index =  Integer.parseInt(sc.nextLine());
		if(index > length) {
			System.out.println("삭제할 수 없습니다.");
		}else {
			if(index == 1) {
				start = start.getNext();
			}else if(index == length) {
				ShapeAbst tmp = start;
				for(int i  = 1; i <length-1; i++) {
					tmp = tmp.getNext();
				}
				tmp.setNext(null);	
			}else if(index < length) {
				ShapeAbst beforedelete = start;
				for(int i = 1; i<index-2;i++) {
					beforedelete = beforedelete.getNext();
				}
				ShapeAbst afterdelete = beforedelete.getNext().getNext();
				beforedelete.setNext(afterdelete);
			}
			length--;
		}
	}
	void insert() {
		System.out.println("1.Line  2.Rect  3.Circle >> ");
		int shape = Integer.parseInt(sc.nextLine());
		if(shape == 1)
			tmp = new Line2();
		else if(shape == 2)
			tmp = new Rect2();
		else if(shape == 3)
			tmp = new Circle2();
		else
			System.out.println("올바른 값을 입력하세요(1~3)");
	
		if(start != null) {
			last.setNext(tmp);
			last = tmp;
		}else {
			start = tmp;
			last = tmp;
		}
		length++;
	}
}
public class Test918 {
	public static void main(String[] args) {
		GraphEditor g = new GraphEditor();
	}
}
