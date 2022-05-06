package day09;

import java.util.Scanner;

class AA {	// x
	int x;
}
class BB extends AA { // x, y 
	int y; 
}

public class Test113 {
	public static void main(String[] args) {

		AA a = new AA(); 
		AA a1 = new BB();

		Object o = new AA(); 
		
		if(o instanceof AA) {
			AA abc = (AA)o; 
		}
		
		
		
		Object o2 = new BB(); 
		Object o3 = new Scanner(System.in);
		
		
	}
}
