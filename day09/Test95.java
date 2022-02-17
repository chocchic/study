package day09;
// 오버라이딩으로 다형성 실현
class Shape{
	public void draw() {
		System.out.println("Shape");
	}
}
class Line extends Shape{
	@Override
	public void draw() {
		System.out.println("Line");
	}
}
class Rect extends Shape{
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}
class Circle extends Shape{
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}
public class Test95 {
	static void paint(Shape s) {
		s.draw(); //shape, line, rect, circle
	}
	public static void main(String[] args) {
		Line line = new Line();
		paint(line);
		paint(new Shape());
		paint(new Rect());
		paint(new Circle());
	}
}
