package day11;
class Point2{
	int x,y;
	public Point2(int x, int y) {
		this.x = x; this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		// 매개변수가 Object 타입으로 받아줘서 x,y 못알아보니
		// 온전히 잘 쓰기위해 Point2타입으로 형변환해서 다시 변수에 담아 사용
		Point2 pobj = null;
		if(obj instanceof Point2) {
			pobj = (Point2)obj;
		}
		if(x == pobj.x && y == pobj.y) {
				return true;
		}
		return false;
	}
}
public class API02 {
	public static void main(String[] args) {
		Point2 a = new Point2(1,3);
		Point2 b = new Point2(1,3);
		Point2 c = a;
		System.out.println(a == b); // 주소값 비교이므로 toString을 오버라이딩 해도 false
		System.out.println(a.equals(b));
		System.out.println(a == c); // 주소 비교
		System.out.println(a.equals(c));
	}
}
