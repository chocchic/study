package com.chocchic.todo.dto;

import java.time.LocalDateTime;

import com.chocchic.todo.model.ToDoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoDTO {
	private String id;
	private String title;
	private boolean done;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	// ToDoEntity를 매개변수로 해서 ToDoDTO를 생성하는 생성자
	// 매개변수에 final 붙인이유 : 값을 변경할 수 없다는것을 가시성을 높여주기 위해
	public ToDoDTO(final ToDoEntity todo) {
		this.id= todo.getId();
		this.title = todo.getTitle();
		this.done =todo.isDone();
		this.regdate = todo.getRegDate();
		this.moddate = todo.getModDate();
	}
	
	// DTO를 Entity로 변환해주는 매서드
	public static ToDoEntity toEntity(final ToDoDTO dto) {
		return ToDoEntity.builder().id(dto.getId()).title(dto.getTitle()).done(dto.isDone()).build();
	}
}
