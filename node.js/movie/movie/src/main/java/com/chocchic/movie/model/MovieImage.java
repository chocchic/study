package com.chocchic.movie.model;

import javax.persistence.Embeddable;
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
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude="movie")
public class MovieImage extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	private String uuid;
	private String imgName;
	private String path;
	
	//JPA에서는 Movie객체를 연결하지만 데이터베이스에서는 Movie테이블의 기본키인 mno를 외래키로 생성
	// 테이블 이름 _mon의 형태로 데이터베이스에는 추가됩니다.
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
}
