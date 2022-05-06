package day14;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjIntConsumer;

public class ConsumEx01 {
	public static void main(String[] args) {

		//Consumer<T>
		Consumer<String> consumer = t -> System.out.println(t + "8"); 
									//void	accept​(T t)
		consumer.accept("java"); 
		
		BiConsumer<String, String> biCon = (t, u) -> System.out.println(t + u);
		biCon.accept("hello", " ramda");
		
		DoubleConsumer dcon = (t) -> System.out.println(t + 3.14);
		dcon.accept(1.1);
		
		ObjIntConsumer<String> objIntCon = (t, i) -> System.out.println(t + i);
		objIntCon.accept("hahaha", 10);
		
		
		
		
		
	}
}
