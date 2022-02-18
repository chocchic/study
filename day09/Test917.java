package day09;

import java.util.Scanner;

//추상 클래스 
/*
	아래와 같이 4개의 멤버(필드와 메소드)를 가진, 4개의 클래스 Add,Sub,Mul,Div 를 작성하세요. 
	- int타입의 a, b 필드 : 2개의 피연산자 저장할 변수
	- void setValue(int a, int b) : 피연산자 값을 객체 내에 저장
	- int calculate() : 클래스의 목적에 맞는 연산을 실행하고 결과를 리턴한다.

	그런데, 각각의 클래스마다 공통된 필드와 메소드가 존재하는 구조이므로, 
	Calc라는 이름의 추상클래스를 작성하여 Calc를 상속받아 각 4개의 클래스를 작성해보세요.
	그리고, main()메소드에서 실행예시와 같이 2개의 정수와 연산자를 입력받은 후,
	4개의 클래스중 적합한 연산을 처리할 수 있는 객체를 생성하고 메서드 호출하여 그 결과 값을 화면에 출력하게 작성 해보세요. 
	실행 예시 :
		두 정수와 연산자를 입력하세요 >> 5 7 +
		12
*/
abstract class Calc{
	protected int a;
	protected int b;
	public abstract int Add();
	public abstract int Sub();
	public abstract int Mul();
	public abstract int Div();
	
	abstract void setValue(int a,int b);
	abstract void calculate();
}
class Calculater extends Calc{

	@Override
	void calculate() {
		Scanner sc = new Scanner(System.in);
		System.out.print("두 정수와 연산자를 입력하세요 >> ");
		int a = Integer.parseInt(sc.nextLine());
		int b = Integer.parseInt(sc.nextLine());
		String op = sc.nextLine();
		setValue(a,b);
		if(op.equals("+")) {
			System.out.println(Add());
		}else if(op.equals("-")) {
			System.out.println(Sub());
		}else if(op.equals("*")) {
			System.out.println(Mul());
		}else if(op.equals("/")) {
			System.out.println(Div());
		}else {
			System.out.println("올바른 연산자를 입력하세요");
		}
		
	}

	@Override
	public int Add() {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public int Sub() {
		// TODO Auto-generated method stub
		return a>b?a-b:b-a;
	}

	@Override
	public int Mul() {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public int Div() {
		// TODO Auto-generated method stub
		return a>b?a/b:b/a;
	}

	@Override
	void setValue(int a, int b) {
		this.a = a; this.b = b;
	}
	
}

public class Test917 {
	public static void main(String[] args) {
		Calculater c = new Calculater();
		c.calculate();
	}
}
