package day13;

import java.net.InetAddress;

public class InetEx {
	public static void main(String[] args) throws Exception {

		InetAddress addr1 = InetAddress.getByName("naver.com");
		InetAddress addr2 = InetAddress.getLocalHost();
		System.out.println(addr1);
		System.out.println(addr2);
		System.out.println(addr1.getHostAddress());
		System.out.println(addr1.getHostName());
		
		
		
		
	}
}
