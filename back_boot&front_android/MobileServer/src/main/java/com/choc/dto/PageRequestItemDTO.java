package com.choc.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestItemDTO {
	//페이지 번호 - 1부터 시작
	private int page;
	//한 페이지에 보여질 데이터 개수
	private int size;
	
	//특정 항목으로 조회하고자 하는 경우
	//private String condition;
	//private String keyword;
	
	//생성자
	public PageRequestItemDTO() {
		page = 1;
		size = 10;
	}
	//페이지 번호 와 데이터 개수를 가지고 Pageable 객체를 생성해주는 메서드
	public Pageable getPageable(Sort sort) {
		//JPA에서는 페이지 번호가 0부터 시작하기 때문에 -1
		return PageRequest.of(page-1, size, sort);
	}
}