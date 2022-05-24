package com.chocchic.todo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chocchic.todo.model.ToDoEntity;
import com.chocchic.todo.persistence.ToDoRepository;

@SpringBootTest
public class ToDoRepoTest {
	@Autowired
	private ToDoRepository t;
	
	// 삽입 확인
	//@Test
	public void testInsert() {
		ToDoEntity todo = ToDoEntity.builder().userId("choc").title("테스트용 제목").build();
		t.save(todo);
	}
	
	// 수정 확인
//	@Test
	public void testUpdate() {
		ToDoEntity todo = ToDoEntity.builder().id("4028b88180f449f10180f44a09910000").userId("choc").title("테스트용 제목 수정됨").build();
		t.save(todo);
	}
	
	// 기본키를 가지고 데이터를 조회
	//@Test
	public void testDetail() {
		// 기본키를 가지고 데이터를 조회
		Optional<ToDoEntity> result = t.findById("4028b88180f449f10180f44a09910000");
		
		// 데이터가 존재할 때
		if(result.isPresent()) {
			System.out.println(result.get());
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}
	
	// 기본키가 아닌 것을 가지고 조회
	//@Test
	public void testList() {
		List<ToDoEntity> result = t.findByUserId("choc");
		//List<ToDoEntity> result = t.findByUserId("c");
		//데이터가 존재할 때
		if(result.size() > 0) {
			for(ToDoEntity e : result) {
				System.out.println(e);
			}
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}
	
	// 삭제는 기본키를 가지고 지우는 것과 Entity를 이용해서 지우는 2가지를 제공
	@Test
	public void testDelete() {
		t.deleteById("4028b88180f449f10180f44a09910000");
	}
}
