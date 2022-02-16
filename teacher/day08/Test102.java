package day08;
// 상속을 이용하여 다음 클래스들을 간결한 구조로 재작성해보세요. 
class Pen {
	private int amount; // 남은 양
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
}
class SharpPencil extends Pen{
	private int width; 	// 펜 굵기
}
class BallPen extends Pen { // amount, color
	private String color; 
	public String getColor() { return color; }
	public void setColor(String color) { this.color = color; }
}
class FountainPen extends BallPen {
	public void refill(int n) { setAmount(n); }
}
public class Test102 {
	public static void main(String[] args) {
		
		Object obj = new Object(); 
		
	}
}





