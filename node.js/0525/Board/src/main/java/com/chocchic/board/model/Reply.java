package com.chocchic.board.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
// ToString을 만들 때 board는 제외 - FetchType.Lazy를 적용하면 에러가 발생
@ToString(exclude = "board")
public class Reply extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rno;
	private String content;
	private String replyer;

	// 다대일 관계 이고 데이터는 처음부터 가져오지 않고 나중에 가져오는 것으로 설정
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
}
