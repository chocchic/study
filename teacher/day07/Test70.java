package day07;

public class Test70 {
	
	// 오버로딩 
	static void add() {}
	static void add(int x) { System.out.println(x); }
	//static void add(int a) {}
	static void add(long a) {} 
	static void add(long a, int b) {}
	static void add(int a, long b) {}
	
	public static void main(String[] args) {

		add();
		add(100);
		add(10, 10L);
		
	}
}




