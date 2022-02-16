package day08;
// 상속을 이용하여 다음 클래스들을 간결한 구조로 재작성해보세요
class SharpPencil2 {
	private int width;
	private int amount;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) { this.amount = amount;}
}
class BallPen2 {
	private int amount;
	private String color;
	public int getAmount() {return amount;}
	public void setAmount(int amount) {this.amount = amount;}
	public String getColor( ) {return color;}
	public void setColor(String color) {this.color = color;}
	
}
class FountainPen2 {
	private int amount;
	private String color;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {this.amount = amount;}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {this.color = color;}
	public void refill(int n) {amount =0;}
}

public class Test82 {
	public static void main(String[] args) {
		
	}
}
