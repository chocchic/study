package day07;
/*
	main() 메서드를 실행하였을때 예시와 같이 출력되도록 Tv클래스를 작성하세요. 
	콘솔출력예 >> LG에서 만든 2022년형 45인치 TV
*/
class Tv {
	String brand; 
	int year;
	int size;
	Tv(String brand, int year, int size){
		this.brand = brand;
		this.year = year;
		this.size = size;
	}
	void show() {
		System.out.println(brand + "에서 만든 " + year + "년형 " + size + "인치 TV");
	}
}
public class Test85 {
	public static void main(String[] args) {
		
		// * main() 메서드는 작성끝. 주석해제하고 실행만 * 
		Tv tv = new Tv("LG", 2022, 45);
		tv.show(); 
		
	}
}
