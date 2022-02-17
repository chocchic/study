package day09;

import java.util.Scanner;

/*
Phone, Mobile 인터페이스를 활용하여 IPhone 작성, Contact 클래스를 체워 넣으세요.  
main메서드 주석해제하고 실행하면 아래 콘솔 출력예시처럼 진행되야합니다. 
콘솔출력예 >>
	iPhone을 구매하셨습니다.
	피카츄님에게서 문자가 도착했습니다.
	msg : 배고프니? 
	피카츄님께 보낼 메세지를 입력하세요 >> 안녕?? (사용자 입력) 
	피카츄님께 보낸메세지를 확인합니다.
	메세지 : 배고파아~ 밥먹자! 
	엄마님 - 010-1111-2222번에서 전화가 왔습니다.
	전화받았습니다. 통화시간은 2분5초입니다.
	꼬북이님, 010-1212-3434 로 전화를 걸고 있습니다...
	
*/
interface Phone {
	void sendCall(Contact contact); 
	void receiveCall(int seconds);
}
interface Mobile extends Phone {
	void sendSMS(Contact contact);
	String receiveSMS(); 
}
class IPhone implements Mobile{

	@Override
	public void sendCall(Contact contact) {
		// TODO Auto-generated method stub
		System.out.println(contact.getName()+"님, " + contact.mobileNo+"로 전화를 걸고 있습니다...");
	}

	@Override
	public void receiveCall(int seconds) {
		// TODO Auto-generated method stub
		System.out.println("전화받았습니다. 통화시간은 " + seconds/60+"분"+seconds%60+"초입니다.");
	}

	@Override
	public void sendSMS(Contact contact) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print(contact.getName() + "님께 보낼 메세지를 입력하세요 >> ");
		String s = sc.nextLine();
		System.out.println(contact.getName() + "님께 보낼 메세지를 확인합니다.");
		System.out.println("메세지 : "+ s);
	}

	@Override
	public String receiveSMS() {
		// TODO Auto-generated method stub
		return "배고프니?";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "iPhone";
	}
	
}
class Contact {
	private String name;
	String mobileNo;
	Contact(String name, String mobileNo){
		this.name = name;
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}

	private Contact toContact() {
		return this;
	}
	
	public String getName() { return name; }
}

public class Test916_0 {
	public static void main(String[] args) {
		
		IPhone iPhone = new IPhone(); 
		Contact pikachu = new Contact("피카츄", "010-1234-5678"); 
		Contact mom = new Contact("엄마", "010-1111-2222"); 
		Contact kkobug = new Contact("꼬북이", "010-1212-3434"); 
		System.out.println(iPhone + "을 구매하셨습니다."); 
		System.out.println(pikachu + "님에게서 문자가 도착했습니다.");
		String msg = iPhone.receiveSMS(); 
		System.out.println("메세지 : " + msg);
		iPhone.sendSMS(pikachu);
		System.out.println(mom + "님 - " + mom.mobileNo + "번에서 전화가 왔습니다.");
		iPhone.receiveCall(125); 
		iPhone.sendCall(kkobug); 
		
	}
}
