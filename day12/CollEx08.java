package day12;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
	CollEx07번 문제를 ArrayList 대신, HashMap을 이용하여 다시 작성해보세요. 
	이때, HashMap의 키값은 학생이름으로 한다. 
*/
public class CollEx08 {
	public static void main(String[] args) {
		HashMap<String, StudentClass> students = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("학생이름, 학과, 학번, 학점 평균 입력하세요.");
		for(int i = 0;i<4;i++) {
			System.out.print(">>");
			String input = sc.nextLine();
			String[] inputSlice = input.split(",");
			int id = Integer.parseInt(inputSlice[2]);
			double score = Double.parseDouble(inputSlice[3]);
			students.put(inputSlice[0],new StudentClass(inputSlice[0],inputSlice[1],id,score));
		}
		Collection values = students.values();
		Iterator it = values.iterator();
		while(it.hasNext()) {
			System.out.println("-----------------------------");
			StudentClass ss = (StudentClass)it.next();
			System.out.println("이름:"+ss.getName()+"\n학과:"+ss.getDept()+"\n학번:"+ss.getId()+"\n학점평균:"+ss.getScore());
		}
		System.out.println("-----------------------------");
		while(true) {
			System.out.print("학생 이름 >> "); String name = sc.nextLine();
			if(name.equals("그만")) {
				System.out.println("프로그램 종료!!");
				break;
			}else {
				it = values.iterator();
				while(it.hasNext()) {
					StudentClass ss = (StudentClass)it.next();
					if(ss.getName().equals(name)) {
						System.out.println(ss.getName()+","+ss.getDept()+","+ss.getId()+","+ss.getScore());
					}
				}
			}
		}
		
		
		
		
	}
}
