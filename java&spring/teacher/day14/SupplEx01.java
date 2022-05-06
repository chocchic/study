package day14;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class SupplEx01 {
	public static void main(String[] args) {

		Supplier<String> supplier = () -> {
			return "hello supplier"; 
		};
		System.out.println(supplier.get());
		
		IntSupplier intSup = () -> {
			int num = (int)(Math.random() * 6) + 1; 
			return num;
		};
		System.out.println(intSup.getAsInt());
		
		
		
		
		
		
		
		
		
		
		
	}
}
