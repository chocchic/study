package day09;
// 링크트 리스트 구현 
class ShapeClass {
	ShapeClass next; 
	ShapeClass() { next = null; }
	void draw() {
		System.out.println("shape");
	}
}
class LineClass extends ShapeClass {
	@Override
	public void draw() {
		System.out.println("Line");
	}
}
class RectClass extends ShapeClass {
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}
class CircleClass extends ShapeClass {
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}
public class Test116 {
	public static void main(String[] args) {

		ShapeClass start, last, tmp; 
		
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
		
		// 모든도형 출력 
		ShapeClass s = start; 
		while(s != null) {
			s.draw();
			s = s.next; 
		}
	
	}
}
