package day15;

public class Student implements Comparable<Student>{
	private String name;
	private int score;
	public Student(String name, int score) {
		this.name = name; this.score = score;
	}
	public String getName() {return name;}
	public int getScore() {return score;}
	
	// Comparable 인터페이스 추상 메서드 오버라이딩
	@Override
	public int compareTo(Student o) {
		return Integer.compare(score, o.score);
		// score < o.score : 음수
		// score == o.score : 0
		// score > o.score : 양수
	}
}