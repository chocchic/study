package day09;
// 매개변수의 다형성 
class Product {
	int price; 		// 제품 가격
	int bonusPoint; // 제품 구매시 제공하는 포인트 점수 
	Product (int price){
		this.price = price; 
		bonusPoint = (int)(price/10); // 제품가격의 10%로 지정 
	}
}
class LGTv extends Product{ // price,bP, Object 11개메서드 
	LGTv(){
		super(100);
	}
	@Override
	public String toString() {
		return "LG Tv";
	}
}
class SComputer extends Product{
	SComputer() {
		super(200);
	}
	@Override
	public String toString() {
		return "Samsung Computer";
	}
}
class Buyer { 			// 고객
	int money = 1000; 	// 지갑
	int bonusPoint = 0; // 고객 포인트 
	void buy(Product p) { // Product p; 매개변수의 다형성 
		if(money < p.price) {
			System.out.println("돈이 모자라네요. 다음에 다시오세요~~ ");
			return; 
		}
		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p + "을/를 구입하셨습니다.");
	}
}
public class Test114 {
	public static void main(String[] args) {
		
		Buyer b = new Buyer(); 
		LGTv tv = new LGTv(); 
		SComputer com = new SComputer(); 
		
		b.buy(tv);  
		b.buy(com); 
		
		System.out.println("현재 남은 돈은 " + b.money + "만원입니다.");
		System.out.println("현재 포인트는 " + b.bonusPoint + "점입니다.");
	
		
	}
}







