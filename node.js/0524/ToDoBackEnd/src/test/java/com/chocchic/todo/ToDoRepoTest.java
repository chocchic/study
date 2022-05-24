package com.chocchic.todo;

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
	@Test
	public void testUpdate() {
		ToDoEntity todo = ToDoEntity.builder().id("4028b88180f449f10180f44a09910000").userId("choc").title("테스트용 제목 수정됨").build();
		t.save(todo);
	}
}
