package day06;
// 계산기 클래스 Calc : 정수 두개를 입력받아 결과를 리턴해주는 형태의 + - * / 메서드를 각각 
//				덧셈 sum, 뺄셈 sub, 곱셈 mul, 나눗셈 div
class Calc {
	int sum(int a, int b) {
		return a + b;
	}
	int sub(int a, int b) {
		return a - b;
	}
	int mul(int a, int b) {
		return a * b;
	}
	double div(int a, int b) {
		if(b == 0) {
			System.out.println("0으로 나눌 수 없습니다.");
			return 0;
		}else {
			return (double)a / b;
		}
	}
}
public class Test62 {
	public static void main(String[] args) {
		
		// 사칙연산 실행 
		Calc c = new Calc(); 
		System.out.println(c.sub(10, 20));
		System.out.println(c.mul(10, 20));
		System.out.println(c.div(10, 0));
		
		
		
		
		
		
	}
}







