package day07;

import java.util.Scanner;

class ConcertBook2{
	String[] s_grade = {"S","A","B"};
	String[][] Seat = new String[3][10];
	Scanner sc = new Scanner(System.in);
	
	ConcertBook2(){
		boolean run = true;
		System.out.println("자바 콘서트 예약 시스템입니다.");
		while(run) {
			System.out.print("예약:1, 조회:2, 취소:3, 끝내기:4 >>  ");
			switch (Integer.parseInt(sc.nextLine())) {
			case 1 :
				boolean booking = true;
				while(booking) {
					booking = book();
				}
				System.out.println("<<예약 완료>>");
				break;
			case 2:
				view();
				System.out.println("<<조회 완료>>");
				break;
			case 3:
				cancel();
				System.out.println("<<취소 완료>>");
				break;
			case 4:
				run = finish();
				break;
			default :
				System.out.println("1~4사이 번호를 입력해주세요.");
				break;
			}
		}
	}
	boolean book() {
		System.out.print("좌석구분 S(1), A(2), B(3) >>  ");
		int grade = Integer.parseInt(sc.nextLine());
		if(4> grade && grade > 0) {
			System.out.print(s_grade[grade-1]+" >>  ");
			for(String seat : Seat[grade-1]) {
				if(seat == null) {
					System.out.print("_______ ");
				}else {
					System.out.print(seat + " ");
				}
			}
			System.out.println();
			System.out.print("이름 >>  ");
			String name = sc.nextLine();
			System.out.print("번호 >>  ");
			int n = Integer.parseInt(sc.nextLine())-1;
			if(Seat[grade-1][n] == null) {
				Seat[grade-1][n] = name; 
				return false;
			}else {
				System.out.println("이미 예약한 사람이 있습니다.");
				return true;
			}
		}else {
			System.out.println("올바른 번호(1~3)를 입력해주세요");
			return true;
		}
	}
	
	void view() {
		for(int i = 0; i<3;i++) {
			System.out.print(s_grade[i]+ " >>  ");
			for(String seat : Seat[i]) {
				if(seat == null) {
					System.out.print("_______ ");
				}else {
					System.out.print(seat);
				}	
			}
			System.out.println();
		}
	}
	void cancel() {
		boolean canceling = true;
		int seatnum = 0;
		while(canceling) {
			System.out.print("좌석구분 S(1), A(2), B(3), 종료를 원하면 4 >>  ");
			int grade = Integer.parseInt(sc.nextLine());
			seatnum = 0;
			if(grade > 0 && 4 > grade ) {
				System.out.print(s_grade[grade-1] + " >>  ");
				for(String seat : Seat[grade-1]) {
					if(seat == null) {
						System.out.print("_______ ");
					}else {
						System.out.print(seat + " ");
						seatnum++;
					}
				}
				System.out.println();
				if(seatnum>0) {
					System.out.print("이름 >>  ");
					String name = sc.nextLine();
					for(int i = 0; i< Seat.length;i++) {
						if(Seat[grade-1][i] == name) {
							Seat[grade-1][i] = null;
							canceling = false;
						}
					}
					System.out.println("올바른 이름을 입력해주세요");
				}else {
					System.out.println("취소 가능한 좌석이 없습니다.");
				}
			}else if(grade == 4){
				System.out.println("예약 취소를 종료합니다.");
				canceling = false;
			}else {
				System.out.println("올바른 번호(1~3)를 입력해주세요");
			}
		}
	}
	boolean finish() {
		System.out.println("<< 프로그램 종료 >>");
		sc.close();
		return false;
	}
}
/*
	콘서트 예약 프로그램.
	- 공연은 하루에 한번
	- 좌석은 S석, A석, B석으로 나뉘며, 각각 10개의 좌석이 있다. 
	- 예약 시스템의 메뉴는 "예약", "조회", "취소", "끝내기"가 있다. 
	- 예약은 한번에 한자리만 가능하고, 좌석타입, 예약자이름, 좌석번호를 입력받아 예약한다. 
	- 조회는 모든 좌석을 출력한다. 
	- 취소는 예약자의 이름을 입력받아 취소한다. 
	- 없는 이름, 없는 번호, 없는 메뉴 등에 대해서 오류 메세지를 출력하고 
		사용자가 다시 시도하도록 한다. 
	# 실행예시 #
	자바콘서트홀 예약시스템입니다. 
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   1  (사용자 입력)
	좌석구분 S(1), A(2), B(3) >>  1   (사용자 입력)
	S >> ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
	이름 >>  피카츄  (사용자 입력)
	번호 >>  1  (사용자 입력)
	<<예약 완료>>
	
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   1  
	좌석구분 S(1), A(2), B(3) >>  2   
	A >> ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
	이름 >>  꼬북이  
	번호 >>  5  
	<<예약 완료>>
	
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   2
	S >> 피카츄 ___ ___ ___ ___ ___ ___ ___ ___ ___
	A >> ___ ___ ___ ___ 꼬북이 ___ ___ ___ ___ ___
	B >> ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
	<<조회 완료>>
	
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   3
	좌석구분 S(1), A(2), B(3) >>  2
	A >> ___ ___ ___ ___ 꼬북이 ___ ___ ___ ___ ___
	이름 >>  꼬북이
	<<취소 완료>>
	
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   2
	S >> 피카츄 ___ ___ ___ ___ ___ ___ ___ ___ ___
	A >> ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
	B >> ___ ___ ___ ___ ___ ___ ___ ___ ___ ___
	<<조회 완료>>
	
	예약:1, 조회:2, 취소:3, 끝내기:4 >>   4
	<<프로그램 종료>>
*/

public class Test721_2 {
	public static void main(String[] args) {
		ConcertBook2 cb = new ConcertBook2();
	}
}
