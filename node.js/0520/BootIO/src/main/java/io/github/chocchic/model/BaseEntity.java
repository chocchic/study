package io.github.chocchic.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

// 공통된 속성을 가진 Entity

// 테이블로 생성할 필요가 없음
@MappedSuperclass
// 데이터베이스 작업을 감시
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
//abstract : 인스턴스를 생성할 수 없도록 해주는 클래스로 상속을 통해서만 사용이 가능
public class BaseEntity {
	// 생성한 시간을 저장하는데 컬럼 이름은 regdate이고 수정할 수 없도록 생성  
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate;
	
	// 수정한 시간을 저장하는데 컬럼이름은 moddate이고 수정할 수 없도록 생성
	@LastModifiedDate
	@Column(name="moddate", updatable=false)
	private LocalDateTime modDate;
	
}
