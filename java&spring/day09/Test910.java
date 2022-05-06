package day09;
// 상속문제
/*
	# 1. 아래 main() 메서드 #1.과 실행결과를 참고하여 TVClass를 상속받은 ColorTV클래스를 작성하세요.
	실행 결과 : 32인치 1024컬러
	# 2. 위 1번 문제를 푼 후, main 메서드 #2. 와 아래 실행 결과를  참고하여 ColorTV를 상속받는 IPTV클래스를 작성하세요
	실행 결과 : 나의 IPTV는 192.0.0.3 주소의 32인치 2048컬러
*/
class TVClass {
	private String made;
	private int size;
	
	public TVClass(int size) { this.size = size; }
	protected int getSize() { return size; }
	
}
class ColorTV extends TVClass{
	private int color;
	public ColorTV(int size) {
		super(size);
	}
	public ColorTV(int size, int color) {
		super(size);
		this.color = color;
	}
	protected int getColor() {return color;}
	public void printProperty() {
		System.out.println(getSize() + "인치 " + color + "컬러");
	}
}
class IPTV extends ColorTV{
	private String addr;
	
	public IPTV(int size) {
		super(size);
	}
	public IPTV(String addr, int size, int color) {
		super(size,color);
		this.addr = addr;
	}
	@Override
		public void printProperty() {
			System.out.println(addr + "주소의 "+ getSize() + "인치 " + getColor() + "컬러");
		}
}

public class Test910 {
	public static void main(String[] args) {
		// # 1.
		ColorTV myTv = new ColorTV(32,1024);
		myTv.printProperty();
		
		// # 2.
		IPTV iptv = new IPTV("192.0.0.3",32,2048);
		iptv.printProperty();
	}
}
