package day14;
// 인터페이스 생성
@FunctionalInterface // 옵션 ( 써도 되고 안써도 된다. 중요한건 함수적 인터페이스가 되기 위해선 추상메서드가 하나만 있어야 함.
public interface MyFuntionalInterface {
//	public void method();
//	public void otherMethod();
//	public void method(int x);
	
	public int method(int x,int y);
}
