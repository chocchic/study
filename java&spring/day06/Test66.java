package day06;
/* TvEx 기능 구현
 * 채널 = 배열[10] 생성 후 랜덤값 10개 생성, 오름차순 정령
 * 채널(up,down)
 * 채널값 입력받아 변경 (없는 채널 번호 입력하면 변경 X)
 * 볼륨(up,down) 
 * 볼륨 음소거 기능
 * Tv정보 출력기능, 전원상태, 볼륨, 채널 값들 출력
*/

class TvEx{
	int[] chs = new int[10];
	int ch = 0;
	int volum = 0;
	boolean power = false;
	
	TvEx(){
		chs[0] = (int)(Math.random()*100)+1;
		for(int i = 1;i<chs.length;i++) {
			int c = (int)(Math.random()*100)+1;
			for(int j = 0; j < i ; j++) {
				if(c==chs[j]) {
					i--;
					break;
				}else {
					chs[i] = c;
				}
			}
		}
		
		for(int i = 0; i<chs.length-1; i++) {
			for(int j = i+1; j<chs.length;j++) {
				if(chs[i]>chs[j]) {
					int tmp = chs[i];
					chs[i] = chs[j];
					chs[j] = tmp;
				}
			}
		}
	}
	
	void tvInfo() {
		if(power) {
			System.out.println("TV 전원 : " + power);
			System.out.println("볼륨 :" + volum);
			System.out.print("채널리스트 :");
			for(int cch : chs) {
				System.out.print(cch + " ");
			}
			System.out.println();
			System.out.println("현재 채널 : " + chs[ch]);
		}
	}
	
	void chUp() {
		if (power) {
			if(ch<10) {
				ch++;
			}else {
				ch = 0;
			}
			System.out.println("현채 채널 : "+chs[ch]);
		}
	}
	void chDown() {
		if (power) {
			if(ch>0) {
				ch--;
			}else {
				ch = 9;
			}
			System.out.println("현채 채널 : "+chs[ch]);
		}
	}
	
	void chChange(int ccc) {
		if(power) {
			for(int i = 0; i<10;i++) {
				if(ccc == chs[i]) {
					ch = i;
				}
			}
			System.out.println("현채 채널 : "+chs[ch]);
		}
	}
	void volUp() {
		if(power){
			if(volum<10) { 
				volum++;
				System.out.println("현재 볼륨 : " + volum);
			}
		}
	}
	void volDown() {
		if(power) {
			if(volum>0) { 
				volum--;
				System.out.println("현재 볼륨 : " + volum);
			}
		}	
	}
	
	void volZero() {
		if(power) {
			volum =0;
			System.out.println("음소거되었습니다.");
		}
	}
	
	void onoff() {
		power = !power;
		if(power) {
			System.out.println("TV가 켜졌습니다.");
		}else {
			System.out.println("TV가 꺼졌습니다.");
		}
	}
}

public class Test66 {
	public static void main(String[] args) {
		TvEx tv = new TvEx();
		tv.onoff();
		tv.tvInfo();
		tv.chUp();
	}
}
