package day09;
// 상속문제 
/*
	#1. 
	아래 main() 메서드 #1.과 실행결과를 참고하여 
	TVClass를 상속받은 ColorTV 클래스를 작성하세요. 
	실행결과 : 32인치 1024컬러
	
	#2.
	위 1번 문제를 푼후, main 메서드 #2와 아래 실행결과를 참고하여 
	ColorTV를 상속받는 IPTV 클래스를 작성하세요. 
	실행결과 : 나의 IPTV는 192.0.0.3 주소의 32인치 2048컬러 
*/
class TVClass {
	private int size;
	public TVClass(int size) { this.size = size; }
	protected int getSize() { return size; }
}
public class Test120 {
	public static void main(String[] args) {

		// #1. 
		//ColorTV myTv = new ColorTV(32, 1024);
		//myTv.printProperty(); 
		
		// #2.
		//IPTV iptv = new IPTV("192.0.0.3", 32, 2048);
		//iptv.printProperty();
		
		
	}
}
