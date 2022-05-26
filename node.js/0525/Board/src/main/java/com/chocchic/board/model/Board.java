package com.chocchic.board.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude="member")
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String content;
	
	// title을 수정하는 메서드
	public void changeTitle(String title) {
		this.title = title;
	}
	
	// content을 수정하는 메서드
	public void changeContent(String content) {
		this.content = content;
	}
	// Member Entity를 N:1관계로 참조
	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;
}
