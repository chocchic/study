package day14;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

class Student {
	private String name; 
	private int engScore;
	private int mathScore;
	public Student(String name, int engScore, int mathScore) {
		this.name = name; this.engScore = engScore; 
		this.mathScore = mathScore;
	}
	public String getName() { return name;  }
	public int getEngScore() { return engScore; }
	public int getMathScore() { return mathScore; }
}

public class FuncEx01 {
	
	// 간단하게 Student 객체들을 생성해서 담은 list를 하나 만듬. 
	private static List<Student> list = Arrays.asList(
				new Student("pika", 90, 98), 
				new Student("raichu", 95, 92)
			);
	
	// 문자열 출력 (이름 출력)
	public static void printString(Function<Student, String> function) {
		for(Student stu : list) {
			System.out.print(function.apply(stu) + "  ");
		}
		System.out.println();
	}
	
	// 정수 출력 (점수 출력) 
	public static void printInt(ToIntFunction<Student> function) {
								// function = t -> t.getEngScore(); 
		for(Student stu : list) {
			System.out.print(function.applyAsInt(stu) + "  ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		System.out.println("# 학생 이름 #");
		printString(t -> t.getName());
		
		System.out.println("# 영어 점수 #");
		printInt(t -> t.getEngScore()); // int applyAsInt(T t)
		
		System.out.println("# 수학 점수 #");
		printInt(t -> t.getMathScore());
		
		
		
		
		
	}
}
