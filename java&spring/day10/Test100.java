package day10;
class SingleT{
	static int a = 10; // 공용변수
	int b = 20;			// 한군데
	
	private static SingleT instance = new SingleT();
	private SingleT() {}
	public static SingleT getInstance() {
		System.out.println("getinstance : " + instance);
		return instance;
	}
	void func() {
		System.out.println("싱글턴");
	}
}

public class Test100 {
	public static void main(String[] args) {
//		SingleT s = new SingleT();
		SingleT s = SingleT.getInstance();
		System.out.println(s);
		s.b = 10000;
		System.out.println(s.b);
		s.func();
	}
}
