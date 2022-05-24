package com.chocchic.todo.service;

import java.util.List;

import com.chocchic.todo.model.ToDoEntity;

public interface ToDoService {
	// 데이터 삽입
	public List<ToDoEntity> create(final ToDoEntity entity);
	// userid에 해당하는 데이터 조회
	public List<ToDoEntity> retrieve(final String userId);
	// id에 해당하는 데이터 조회
	public ToDoEntity detail(final String id);
	// 데이터 수정
	public List<ToDoEntity> update(final ToDoEntity entity);
	// 데이터 삭제
	public List<ToDoEntity> delete(final ToDoEntity entity);
	
	/*
	지난번에는 DTO와 Entityt의 변환을 Service에서 했습니다. 
	장점은 DTO와 Entity변환 작업을 Service에서 호출하기 대문에 자기한테 만들어져 있으면 코드를 읽기가 편합니다.
	단점은 Business Logic과 그렇지 않은 로직이 한 곳에 있어서 역할의 경계가 애매해지고 유지보수가 어려워질 수 있습니다.
	*/
}
