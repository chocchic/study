package day03;

public class Test_for {
	public static void main(String[] args) {
		//#1
		/*
		for(int i = 1; i<6;i++) {
			System.out.println(i+"헹 : ***");
		}
		*/
		//#2
		/*
		for(int i = 1;i<6;i++) {
			for(int j = i; j>0;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		//#3
		/*
		for(int i = 5; i>0;i--) {
			for(int j =i; j>0;j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		//#4
		/*
		for(int i = 1; i<6;i++) {
			for(int j = i;j<i+5;j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		*/
		//#5
		/*
		for(int i = 5; i>0;i--) {
			for(int j = i; j<i+5;j++) {
				System.out.print(j);
			}
			System.out.println();
		}
		*/
		//#6
		/*
		for(int i = 1;i<10;i++) {
			if(i<=5) {
				for(int j = 0; j < i;j++) {
					System.out.print("*");
				}
			}else {
				for(int j = 0;j<10-i;j++) {
					System.out.print("*");
				}
			}
			System.out.println();
			
		}
		*/
		//#7
		/*
		for(int i = 1;i<6;i++) {
			for(int j = 0;j<5-i;j++) {
				System.out.print(" ");
			}
			for(int k = 0;k<i;k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		//#8
		/*
		for(int i = 1; i < 6;i++) {
			for(int k = 1; k<i;k++) {
				System.out.print(" ");
			}
			for(int j = 0; j<6-i; j++) {
				System.out.print("*");
			}
			
			System.out.println();
		}
		*/
		//#9
		/*
		for(int i = 1; i<12;i++) {
			if(i <= 6) {
				for(int j = i;j<6;j++) {
					System.out.print(" ");
				}
				for(int k = 0; k < i; k++) {
					System.out.print("*");
				}
			}else {
				for(int j = 0;j<i-6;j++) {
					System.out.print(" ");
				}
				for(int k = 0; k < 12-i; k++) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		*/
		//#10
		/*
		for(int i = 0; i< 5; i++) {
			for(int j = i+1; j<5; j++) {
				System.out.print(" ");
			}
			for(int k = 2*i+1;k>0;k--) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		//#11
		/*
		for(int i = 5; i >= 0;i--) {
			for(int j = i;j<5;j++) {
				System.out.print(" ");
			}
			for(int k = 2*i+1;k>0;k--) {
				System.out.print("*");
			}
			System.out.println();
		}
		*/
		//#12
		/*
		for(int i = 0; i < 9; i++) {
			if(i<5) {
				for(int j = 0;j<5-i;j++) {
					System.out.print("*");
				}
			}else {
				for(int k = i-3; k >0;k--) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		*/
		//#13
		/*
		for(int i = 0; i < 9; i++) {
			if(i<5) {
				for(int m = 0;m<i;m++) {
					System.out.print(" ");
				}
				for(int j = 0;j<5-i;j++) {
					System.out.print("*");
				}
			}else {
				for(int l = 0;l<8-i;l++) {
					System.out.print(" ");
				}
				for(int k = i-3; k >0;k--) {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		*/
		//#14
		/*
		for(int i = 0; i< 9; i++) {
			if(i<5) {
				for(int j = i+1; j<5; j++) {
					System.out.print(" ");
				}
				for(int k = 2*i+1;k>0;k--) {
					System.out.print("*");
				}
			}else {
				for(int j = 0; j<i-4; j++) {
					System.out.print(" ");
				}
				for(int k = 2*(8-i)+1;k>0;k--) {
					System.out.print("*");
				}
			}
			
			System.out.println();
		}
		*/
		//#15
		/*
		for(int i = 0;i<9;i++) {
			if(i<5){
				for(int j=0;j<4-i;j++) {
					System.out.print("*");
				}
				for(int k=0;k<i*2+1;k++) {
					System.out.print(" ");
				}
				for(int j=0;j<4-i;j++) {
					System.out.print("*");
				}
			}else {
				for(int j=0;j<i-4;j++) {
					System.out.print("*");
				}
				for(int k=0;k<(8-i)*2+1;k++) {
					System.out.print(" ");
				}
				for(int j=0;j<i-4;j++) {
					System.out.print("*");
				}
			}
			System.out.println();	
		}
		*/
		//#16
		/*
		for(int i = 1;i<10;i++) {
			if(i<5) {
				for(int j = 0;j<i;j++) {
					System.out.print("*");
				}
				for(int k = 0;k<2*(4-i)+1;k++) {
					System.out.print(" ");
				}
				for(int j = 0;j<i;j++) {
					System.out.print("*");
				}
				System.out.println();
			}else if(i==5) {System.out.println("*********");}
			else {
				for(int j = 0;j<10-i;j++) {
					System.out.print("*");
				}
				for(int k = 0;k<2*(i-6)+1;k++) {
					System.out.print(" ");
				}
				for(int j = 0;j<10-i;j++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		*/
		//#17
		/*
		for(int i = 1;i<11;i++) {
			if(i<=5) {
				for(int j= 1;j<i;j++) {
					System.out.print(" ");
				}
				for(int k=0;k<2*(5-i)+1;k++){
					System.out.print("*");
				}
			}else {
				for(int j= 1;j<11-i;j++) {
					System.out.print(" ");
				}
				for(int k=0;k<2*(i-6)+1;k++){
					System.out.print("*");
				}
			}
			System.out.println();
		}
		*/
		//#17-1
		/*
		for(int i = 1;i<10;i++) {
			if(i<=5) {
				for(int j= 1;j<i;j++) {
					System.out.print(" ");
				}
				for(int k=0;k<2*(5-i)+1;k++){
					System.out.print("*");
				}
			}else {
				for(int j= 1;j<10-i;j++) {
					System.out.print(" ");
				}
				for(int k=0;k<2*(i-5)+1;k++){
					System.out.print("*");
				}
			}
			System.out.println();
		}
		*/
		//#18
		System.out.println("\t\t\t    구구단");
		System.out.println("\n\n");
		for(int i = 2;i<7;i+=4) {
			for(int j = 1;j<10;j++) {
				System.out.print(i + " x " + j +" = " + i*j +"\t  ");
				System.out.print((i+1) + " x " + j +" = " + (i+1)*j +"\t  ");
				System.out.print((i+2) + " x " + j +" = " + (i+2)*j+"\t  ");
				System.out.println((i+3) + " x " + j +" = " + (i+3)*j);
			}
			System.out.println("\n\n");
		}
	}
	
}
