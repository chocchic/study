package day14;
// 인터페이스 생성
@FunctionalInterface  // 옵션 
//써도 되고 안써도 되고, 
//중요한건 함수적인터페이스가 되기위해서는 추상메서드가 1개만 있어야함
public interface MyFunctionalInterface {

	//public void method(); 
	//public void otherMethod(); 
	
	//public void method(int x); 
	
	public int method(int x, int y); 
	
}
