package day09;
// 매개변수의 다형성
class Product{
	int price; // 제품 가격
	int bonusPoint; // 제품에 따른 포인트
	Product(int price){
		this.price = price;
		bonusPoint = (int)(price/10);
	}
}

class LGTv extends Product{
	LGTv(){
		super(100);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "LG TV";
	}
	
}
class SCom extends Product{
	SCom(){
		super(200);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "Samsung Computer";
	}
}
class Buyer{ // 고객
	int money = 1000; //지갑
	int bonusPoint = 0; // 고객 포인트
	void buy(Product p) { // 매개 변수의 다형성
		if(money < p.price) {
			System.out.println("잔액 부족");
			return;
		}
		money -= p.price;
		bonusPoint += p.bonusPoint;
		System.out.println(p + "을/를 구입하셨습니다.");
	}
}
public class Test94 {
	public static void main(String[] args) {
		Buyer b = new Buyer();
		LGTv tv = new LGTv();
		SCom com = new SCom();
		
		b.buy(tv);
		System.out.println("현재 남은 돈은 " + b.money + "만원 입니다.");
		System.out.println("현재 포인트는 " + b.bonusPoint+"점입니다.");
		b.buy(com);
	};

}
