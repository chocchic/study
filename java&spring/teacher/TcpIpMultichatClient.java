package day13;
import java.net.*; 
import java.io.*;
import java.util.Scanner;
// ctrl+3 실행
public class TcpIpMultichatClient {
	public static void main(String args[]) {
		if(args.length!=1) {
			System.out.println("USAGE: java TcpIpMultichatClient 대화명");
			System.exit(0);
		}

		try {
			String serverIp = "192.168.10.54";
            // 소켓을 생성하여 받은 ip주소와 호스트로 연결한다. 
			// Socket(String host, int port) 
			Socket socket = new Socket(serverIp, 7777); 
			System.out.println("서버에 연결되었습니다.");
			// 스레드로 한명씩 관리. 인풋 아웃풋 두개 돌림.
							// Thread(Runnable target) : 새로운 스레드 객체 할당 (소켓과 대화명던져생성) Thread는 Runnable인터페이스 구현한것.
			Thread sender   = new Thread(new ClientSender(socket, args[0]));
			Thread receiver = new Thread(new ClientReceiver(socket));

			sender.start();	//run() 메서드 호출
			receiver.start();
		} catch(ConnectException ce) {
			ce.printStackTrace();
		} catch(Exception e) {}
	} // main

	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out; //데이터 쓰기위한 아웃풋스트림 
		String name;
					// 생성한 소켓전달받기, 대화명 받기
		ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
					// DataOutputStream(OutputStream out) : 데이터쓰기위해 아웃풋스트림 생성
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			} catch(Exception e) {}
		}
		// main에서 start()하면 호출되는 run()메소드
		public void run() {
			Scanner scanner = new Scanner(System.in);
			try {
				if(out!=null) {
					out.writeUTF(name);
				}	

				while(out!=null) {
					out.writeUTF("["+name+"]"+scanner.nextLine());					}
			} catch(IOException e) {}
		} // run()
	} // ClientSender

	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch(IOException e) {}
		}

		public void run() {
			while(in!=null) {
				try {
					System.out.println(in.readUTF());
				} catch(IOException e) {}
			}
		} // run
	} // ClientReceiver
} // class