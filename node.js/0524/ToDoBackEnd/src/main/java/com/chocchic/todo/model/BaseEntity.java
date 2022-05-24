package com.chocchic.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

// 데이터베이스 작업을 감시하도록 설정
// Listener: 이벤트가 발생했을 때 처리하는 객체
@EntityListeners(value= {AuditingEntityListener.class})
// 테이블로 생성하지 않는 Entity를 생성하기 위한 설정
@MappedSuperclass
// 인스턴스를 만들 수 없는 클래스
@Getter
abstract class BaseEntity {
	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modDate;
}
