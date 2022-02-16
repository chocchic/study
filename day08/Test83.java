package day08;
class Person{
	private int weight;
	int age;
	protected double height;
	public String name;
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getWeight() {return weight;}
	
}
class Student extends Person{ //weight, age, height, name, setWeight(), getWeight(),set()
	public void set() {
		age = 10;
		name = "피카츄";
		height = 100.44;
		setWeight(100);
	}
}

public class Test83 {
	public static void main(String[] args) {
		
	}

}
