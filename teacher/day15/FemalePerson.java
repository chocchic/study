package day15;

import java.util.ArrayList;
import java.util.List;

public class FemalePerson {

	private List<Person> list;  // 요소 저장할 컬렉션 
	
	public FemalePerson() {
		list = new ArrayList<Person>();
	}
	// getter 
	public List<Person> getList() { return list; }
	
	// 요소를 list에 수집해주는 메서드 
	public void accumulate(Person person) {
		list.add(person); 
	}
	
	// 두개 FemalePerson 을 결합해주는 메서드 
	public void combine(FemalePerson other) {
		list.addAll(other.getList()); 
	}
	
	
}
