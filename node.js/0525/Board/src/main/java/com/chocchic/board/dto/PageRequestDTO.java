package com.chocchic.board.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	// 현재페이지 번호
	private int page;
	// 페이지당 출력할 데이터 개수
	private int size;
	// 검색 항목
	private String type;
	// 검색할 데이터
	private String keyword;
	
	// 생성자
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page-1, size, sort);
	}
}
