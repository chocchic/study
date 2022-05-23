package io.github.chocchic.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PageRequestDTO {
	// 페이지 번호
	private int page;
	// 한 페이지에 출력될 데이터 개수
	private int size;
	
	// 생성자 - 인스턴스 변수를 초기화
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	// Pageable(Spring Boot JPA에서 Page 단위 검색을 위한 클래스) 객체를 생성해주는 메서드
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
}
