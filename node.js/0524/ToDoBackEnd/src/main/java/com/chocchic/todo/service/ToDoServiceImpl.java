package com.chocchic.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chocchic.todo.model.ToDoEntity;
import com.chocchic.todo.persistence.ToDoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service // Autowired 쓰려면 해야함
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService{
	
	private final ToDoRepository todoRepository;
	
	// 유효성검사 메서드 - 인터페이스에 만들어도 되고 ServiceImpl에 만들어도 되는데 인터페이스에 만들면 public으로 만들어야해서 외부에서 호출할 수 있게 됩니다.
	// 외부에서 호출할 수 없도록 할 때는 ServiceImpl에서 private으로 구현하고, 외부에서 호출 가능하도록하고자하면 Service인터페이스에서 default로 만들면됩니다.
	private void validate(final ToDoEntity entity) {
		if(entity== null) {
			log.warn("Entity is null");
			throw new RuntimeException("Entity cannot be null");
		}
		if(entity.getUserId() == null) {
			log.warn("Unknown User");
			throw new RuntimeException("Unknown User");
		}
	}
	
	@Override
	public List<ToDoEntity> create(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터 삽입
		todoRepository.save(entity);
		// 삽입한 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> retrieve(String userId) {
		// 유저의 데이터를 전부 조회해서 리턴
		return todoRepository.findByUserId(userId);
	}

	@Override
	public ToDoEntity detail(String id) {
		ToDoEntity todo = null;
		Optional<ToDoEntity> result = todoRepository.findById(id);
		if(result.isPresent()) {
			todo = result.get();
		}
		return todo;
	}

	@Override
	public List<ToDoEntity> update(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo =detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.save(entity);
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

	@Override
	public List<ToDoEntity> delete(ToDoEntity entity) {
		// 유효성 검사
		validate(entity);
		// 데이터가 데이터베이스상 존재 여부 확인
		ToDoEntity todo = detail(entity.getId());
		// 데이터가 존재하면 수정
		if(todo != null) {
			todoRepository.delete(entity);
//			todoRepository.deleteById(entity.getId()); // id로 지우기도 가능
		}
		// 유저의 모든 데이터 리턴
		return todoRepository.findByUserId(entity.getUserId());
	}

}
