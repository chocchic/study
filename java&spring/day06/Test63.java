package day06;
// Tv 클래스
// 값 저장 : 채널, 볼륨, 전원
// 기능 : 전원on/off, 볼륨 조정, 채널 조정 ...
class Tv{
	int ch = 1,vol = 0;
	boolean power = false;
	void onoff() {
		power=!power;
	}
	void chUp() {
		ch++;
	}
	void chDown() {
		ch--;
	}
	void volDonw() {
		vol--;
	}
	void volUp() {
		vol++;
	}
	void chChange(int cch) {
		ch = cch;
	}
}

public class Test63 {
	public static void main(String[] args) {
		Tv tv = new Tv();
		System.out.println(tv.power);
		tv.chUp();
		System.out.println(tv.ch);
		tv.volDonw();
		System.out.println(tv.vol);
		tv.chChange(50);
		System.out.println(tv.ch);
	}
}
