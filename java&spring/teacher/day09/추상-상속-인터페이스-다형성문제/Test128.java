package day09;
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

public class Test128 {
	public static void main(String[] args) {

	}
}
