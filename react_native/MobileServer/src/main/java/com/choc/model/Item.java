package com.choc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="member") // member.toString은 제외
public class Item {
	@Id
	// 기본키 값을 auto_increment나 sequence를 이용해서 자동 생성
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemid;
	@Column(length=100, nullable = false)
	private String itemname;
	@Column
	private Integer price;
	@Column(length = 200)
	private String description;
	@Column(length = 255)
	private String pictureurl;
	
	// 사용을 할 때 데이터를 가져오겠다는 옵션
	@ManyToOne(fetch=FetchType.LAZY)
	private Member member;
}
