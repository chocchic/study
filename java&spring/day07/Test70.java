package day07;

public class Test70 {
	//오버로딩
	static void add() { }
	//static void add(int x) {System.out.println();} //인자 개수 같음, 타입도 같음 -> 불가능
	static void add (long a) {}
	static void add(long a, long b) {}
	static void add(long a, int b) {}
	static void add(int a, long b) {}
	
	public static void main(String[] args) {
		add(); //1번째
		add(100); // 2번째
		add(10L,10);//4번째
		add(10,10L);//5번째
	}
}
