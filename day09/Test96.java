package day09;
// 링크드 리스트 구현
class ShapeClass{
	ShapeClass next; // 다음 배열의 주소값 저장
	ShapeClass() { next = null; }
	void draw() {
		System.out.println("shape");
	}
}
class LineClass extends ShapeClass{ 
//	ShapeClass next; // 눈에는 안보이지만 상속받아서 있음. 부모클래스에서 부모클래스형으로 선언해놨기 떄문에 배열에 자식클래스 아무거나 다 받을 수 있다
	public void draw() {
		System.out.println("Line");
	}
	
}
class RectClass extends ShapeClass{
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}
class CircleClass extends ShapeClass{
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}

public class Test96 {
	public static void main(String[] args) {
		ShapeClass start, last,tmp; // 리스트를 시작할 start, 리스트의 마지막 개체라 링크를 연결해야하는 last, 링크에 새로 추가될 임시 변수 tmp
		
		start = new LineClass();
		last = start;
		tmp = new RectClass();
		last.next = tmp;
		last = tmp;
		tmp = new LineClass();
		last.next = tmp;
		last = tmp;
		tmp = new CircleClass();
		last.next = tmp;
		last = tmp;
		
		// 모든 도형 출력
		ShapeClass s = start;
		while(s!=null) {
			s.draw();
			s= s.next;
		}
	}
}
