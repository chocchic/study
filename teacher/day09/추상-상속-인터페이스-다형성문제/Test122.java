package day09;
//상속, 생성자
/*
	Point를 상속받아 색을 가진 점을 나타내는 ColorPoint 클래스를 작성하세요. 
	main()메소드 적힌 코드들을 포함하고 아래와 같은 실행결과 출력되게 만드세요. 
	실행결과:
		RED색의 (10,20)의 점입니다.
*/
class Point {
	private int x, y;
	public Point(int x, int y) { this.x = x; this.y = y; }
	public int getX() { return x; }
	public int getY() { return y; }
	protected void move(int x, int y) { this.x = x; this.y = y; }
}

public class Test122 {
	public static void main(String[] args) {

		//ColorPoint cp = new ColorPoint(5, 5, "Yellow");
		//cp.setXY(10,20);
		//cp.setColor("RED");
		//String str = cp.toString();
		//System.out.println(str+"입니다.");

	}
}
