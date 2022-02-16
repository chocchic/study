package day08;
// 상속을 이용하여 다음 클래스들을 간결한 구조로 재작성해보세요
class Stationary{
	private int amount;
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount;}
}

class BallPen extends Stationary{
	private String color;
	public String getColor( ) {return color;}
	public void setColor(String color) {this.color = color;}
}

class SharpPencil extends Stationary{
	private int width;
}

class FountainPen extends BallPen{
	public void refill(int n) {setAmount(0);}
}

public class Test82_2 {
	public static void main(String[] args) {
		
	}
}
