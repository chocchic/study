package day11;
class A{}

class Point{
	int x, y;
	public Point(int x, int y){
		this.x = x; this.y = y; 
	}
	@Override
	public String toString() {
//		return "(" + x + "," + y + ")";
		return "Point class : " + getClass().getName() + "@"+Integer.toHexString(hashCode());
//		return "Point class : " + super.toString();
	}
}
public class API01 {
	public static void print(Point p) {
		System.out.println(p.getClass().getName());
		System.out.println(p.hashCode());
		System.out.println(p.toString());
		System.out.println(p);
	}
	public static void main(String[] args) {
//		A a = new A();
//		System.out.println(a.getClass());
//		System.out.println(a.hashCode());
//		System.out.println(a.toString());
		
		Point p = new Point(2,5);
		print(p);
		
		Point p2 = new Point(1,3);
		print(p2);
	}
}
