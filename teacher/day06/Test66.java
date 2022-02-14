package day06;
/* TvEx 기능 구현 
0. on/off, vol조정, ch조정
v1. 채널 = 배열[10] 생성후 랜덤값 10개 생성후, 오름차순으로 정렬 
v2. 채널 (up, down) 기능 
v3. 채널값 입력받아 변경 (없는 채널번호 입력하면 변경X) 
v4. 볼륨 (up, down) 0 ~ 10 사이 증감 
v5. 볼륨 음소거 기능 
v6. Tv정보출력 기능. 전원상태, 볼륨, 채널 값들 출력
7. 전원이 꺼져있는 경우 모든 기능 동작 안되게 
*/
class TvEx {
	boolean power = false; 
	int vol = 0; 
	int [] channel = new int[10]; // 랜덤 채널들 목록 가지고 있을 배열 
	int chIdx = 0;  // 현재 채널을 배열상의 idx번호로 가지고 있을 변수 
	//int ch = 0; 
	
	// 전원 
	void onOff() {
		power = !power;
	}
	
	// 채널 세팅 
	void setChannel() {
		if(power) {
			// 랜덤 10개 체워주기 (중복 체크) 
			for(int i = 0; i < channel.length; i++) {
				channel[i] = (int)(Math.random() * 50) + 1; 
				for(int j = 0; j < i; j++) { // 중복체크 
					if(channel[i] == channel[j]) {
						i--; 
						break;
					}
				}
			}
			// 정렬 
			for(int i = 0; i < channel.length-1; i++) {
				for(int j = i + 1; j < channel.length; j++) {
					if(channel[i] > channel[j]) {
						int tmp = channel[i];
						channel[i] = channel[j];
						channel[j] = tmp; 
					}
				}
			}
			// 출력해 확인 
			for(int c : channel) {
				System.out.print(c + "  ");
			}
			System.out.println();
		}else {
			System.out.println("전원을 키고 실행해주세요.");
		}
	}
	// 채널
	void chUp() {
		if(power) {
			chIdx++; // 0 ~ 9
			if(chIdx == 10) { chIdx = 0; }
		}else {
			System.out.println("전원을 켜고 실행해주세요.");
		}
	}
	void chDown() {
		chIdx--; // 0 ~ 9 
		if(chIdx == -1) { chIdx = 9; }
	}
	void channelChange(int c) {
		for(int i = 0; i < channel.length; i++) {
			if(channel[i] == c) {
				chIdx = i;
				break;
			}
		}
	}
	
	// 볼륨 
	void volUp() {
		if(vol < 10) { vol++; }
	}
	void volDown() {
		if(vol > 0) { vol--; } 
	}
	void mute() {
		vol = 0;
	}
	// Tv정보기능 출력 
	String tvStatus() {
		 return "전원 : " + (power?"ON":"OFF") + ", 채널 : " + channel[chIdx] + ", 볼륨 : " + vol; 
	}
}
public class Test66 {
	public static void main(String[] args) {

		// 프로그램 실행시 시작지점
		TvEx tv = new TvEx(); 
		tv.onOff();
		tv.setChannel();
		System.out.println(tv.tvStatus());
//		tv.chDown();
//		System.out.println(tv.tvStatus());
//		tv.channelChange(10);
//		System.out.println(tv.tvStatus());
		
		
		
		
	}
}
