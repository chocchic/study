package day11;

public class API05 {
	public static void main(String[] args) {
		Integer i = new Integer(20); // 취소선은 이런 방식을 추천하지 않는다는 뜻
		int ii = i.intValue();
		System.out.println(ii);
		int iii = i;
		System.out.println(iii);
		Character c = new Character('c');
		Double d = new Double("3.14");
		Boolean b = new Boolean(true);
		
		// 문자열 -> 기본형
		int a = Integer.parseInt("123");
		boolean bb = Boolean.parseBoolean("true");
		double dd = Double.parseDouble("3.141592");
		
		int x = 10;
		Object obj = x;
		System.out.println(obj.getClass());
	}
}
