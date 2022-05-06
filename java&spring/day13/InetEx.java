package day13;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetEx {
	public static void main(String[] args) {
		try {
			InetAddress addr1 = InetAddress.getByName("naver.com");
			System.out.println(addr1);
			System.out.println(addr1.getHostAddress());
			System.out.println(addr1.getHostName());
			InetAddress addr2 = InetAddress.getLocalHost();
			System.out.println(addr2);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
