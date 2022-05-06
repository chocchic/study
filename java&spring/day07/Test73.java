package day07;

class Product {
	static int count = 0;
	String serialNo;
	
	// 인스턴스 초기화 블럭
	{
		++count;
		serialNo = "P"+count;
	}
}

public class Test73{
	public static void main(String[] args) {
		Product p1 = new Product();
		Product p2 = new Product();
		Product p3 = new Product();
		
		System.out.println("p1 제품의 제품 번호는 " + p1.serialNo);
		System.out.println("p2 제품의 제품 번호는 " + p2.serialNo);
		System.out.println("p3 제품의 제품 번호는 " + p3.serialNo);
		System.out.println("생산된 전체 제품 개수 : " + Product.count);
	}
}