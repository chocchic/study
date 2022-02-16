package day07;

class Sample{
	public int a;
	private int b;
	int c;
	protected int d;
}

public class Test712 {
	public static void main(String[] args) {
		Sample s = new Sample();
		s.a = 100;
		//s.b = 200; // error
		s.c = 300;
		s.d = 400;
	}
}
