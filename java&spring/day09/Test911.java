package day09;
import java.util.Scanner;
//추상클래스
/*
	#1. Converter클래스를 상속받아 원화를 달러로 변환하는 Won2Dollar 클래스를 작성하세요. 
		main()메소드의 #1번 코드와 아래 콘솔 출력예를 참고하세요. 
		콘솔출력예)
			원을 달라로 바꿉니다.
			원을 입력하세요>>  24000 (<-사용자가 값 입력)
			변환결과: 20.0달러 입니다
			
	#2. Converter 클래스를 상속받아 Km를 Mile(마일)로 변환하는 Km2Mile 클래스를 작성하세요. 
		main()메소드 #2번 코드와 아래 콘솔 출력예를 참고하세요.
		콘솔출력예)
			Km을 mile로 바꿉니다.
			Km을 입력하세요>>  30(<-사용자가 깂 입력)
			변환결과: 18.75mile입니다
*/

abstract class Converter {
	abstract protected double convert(double src);		// 추상메소드
	abstract protected String getSrcString();			// 추상메소드
	abstract protected String getDestString();			// 추상메소드
	protected double ratio;								// 비율
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		System.out.println(getSrcString() + "을 "+getDestString()+ "로 바꿉니다.");
		System.out.print(getSrcString() + "을 입력하세요>> ");
		double val = scanner.nextDouble();
		double res = convert(val);
		System.out.println("변환결과: "+res+getDestString()+"입니다");
	}
	
}
class Won2Dollar extends Converter{
	int ratio;
	Won2Dollar(int ratio){
		this.ratio = ratio; 
	}
	@Override
	protected double convert(double src) {
		// TODO Auto-generated method stub
		return src/ratio;
	}

	@Override
	protected String getSrcString() {
		// TODO Auto-generated method stub
		return "원";
	}

	@Override
	protected String getDestString() {
		// TODO Auto-generated method stub
		return "달러";
	}
	
}

class Km2Mile extends Converter{
	double ratio;
	Km2Mile(double ratio){
		this.ratio = ratio; 
	}
	@Override
	protected double convert(double src) {
		// TODO Auto-generated method stub
		return src/ratio;
	}

	@Override
	protected String getSrcString() {
		// TODO Auto-generated method stub
		return "Km";
	}

	@Override
	protected String getDestString() {
		// TODO Auto-generated method stub
		return "Mile";
	}
	
}

public class Test911 {
	public static void main(String[] args) {
		//#1.
		Won2Dollar toDollar = new Won2Dollar(1200);		// 1달러는 1200원
		toDollar.run();
		
		//#2. 
		Km2Mile toMile = new Km2Mile(1.6);			// 1마일은 1.6km
		toMile.run();
	}
}
