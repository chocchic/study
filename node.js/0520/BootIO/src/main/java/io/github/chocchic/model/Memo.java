package io.github.chocchic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="memo")

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo extends BaseEntity{
	/*
	// 시스템이 생성해주는 랜덤한 문자열
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	*/
	// gno 값을 데이터베이스의 auto_increment나 sequence를 이용해서 생성
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gno;
	
	@Column(length=100, nullable=false)
	private String title;
	
	@Column(length=1500, nullable=false)
	private String content;
	
	@Column(length=100, nullable = false)
	private String writer;
	
	// title을 변경해주는 메서드
	public void changeTitle(String title) {
		this.title = title;
	}
	
	// content를 변경해주는 메서드
	public void changetContent(String content) {
		this.content = content;
	}
}
