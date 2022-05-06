package day14;

import java.util.function.Consumer;
import java.util.function.Function;

class Member{
	private String name;
	private String id;
	private Address addr;
	public Member(String name, String id, Address addr) {
		this.name = name; this.id = id; this.addr = addr;
	}
	public String getName() {return name;}
	public String getId() {return id;}
	public Address getAddr() {return addr;}
}
class Address{
	private String country;
	private String city;
	public Address(String country, String city) {
		this.country = country; this.city = city;
	}
	
	public String getCountry() {return country;}
	public String getCity() {return city;}
}

public class ConsumAndThen {
	public static void main(String[] args) {
		
		//void accept(T t)
		/*
		Consumer<Member> consumerA = (m) -> {
			System.out.println("consumerA : " + m.getName());
		};
		Consumer<Member> consumerB = (m) -> {
			System.out.println("consumerB : " + m.getName());
		};
		
		Consumer<Member> consumerAB = consumerA.andThen(consumerB);
		consumerAB.accept(new Member("pika", "chuid", null));
		*/
		
		Function<Member, Address> functionA;
		Function<Address,String> functionB;
		Function<Member, String> functionAB;
		
		//R apply(T t)
		functionA = (m) -> m.getAddr(); // Member 받아서 Address 객체 리턴
		functionB = (a) -> a.getCity(); // Address 받아서 String city 리턴
		functionAB = functionA.andThen(functionB);
		
		String city = functionAB.apply(new Member("pikachu","pikaid",new Address("korea","seoul")));
		System.out.println("거주 도시 : "+ city);
	}
}
