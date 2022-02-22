package day12;

import java.util.ArrayList;
import java.util.Scanner;

/*
	Scanner 클래스를 사용하여 6개 학점(학점단위 : 'A','B','C','D','F')을 문자로 입력받아
	ArrayList에 저장하고 ArrayList를 검색하여 학점을 점수(A=4.0, B=3.0, C=2.0, D=1.0, F=0)로 변환하여 
	평균을 출력하는 프로그램을 작성하세요. 
	콘솔예:
		6개의 학점을 띄어쓰기하여 입력하세요(A/B/C/D/F) >> A C A B F D  (사용자 입력)
		평균 : 2.333333333
		
*/
public class CollEx03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("6개의 학점을 띄어쓰기하여 입력하세요(A/B/C/D/F) >> ");
		String grades = sc.nextLine();
		ArrayList<String> grade = new ArrayList<String>();
		String[] s = grades.split(" ");
		for(String ss : s) {
			grade.add(ss);
		}
		double[] score = {4.0,3.0,2.0,1.0,0,0};
		double total = 0;
		int num = 0;
		for(String g : grade) {
			if(70>= (int)g.charAt(0) && (int)g.charAt(0)>=65) {
				total+=score[((int)g.charAt(0))-65];
				num++;
			}
		}
		System.out.println("평균 : " + total/num);
	}
}
