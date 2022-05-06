package day12;

import java.util.Vector;

class Point {
	private int x,y;
	public Point(int x, int y) {
		this.x = x; this.y = y;
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y+ ")";
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}

public class VecTest02 {
	public static void main(String[] args) {
		Vector<Point> v = new Vector<Point>();
		
		v.add(new Point(2,3));
		v.add(new Point(10,20));
		v.add(new Point(-100,-300));
		
		System.out.println(v);
		
		for(int i = 0; i< v.size(); i++) {
			System.out.println(v.get(i).getX());
			System.out.println(v.get(i).getY());
//			Point p = v.get(i);
//			System.out.println(p);
//			System.out.println(p.getX());
//			System.out.println(p.getY());
//			System.out.println();
		}
	}
}
