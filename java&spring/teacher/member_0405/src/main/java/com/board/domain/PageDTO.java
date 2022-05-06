package com.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage; 
	private int endPage;
	private boolean prev, next; 
	
	private int total;		// 전체 글의 개수 
	private Criteria cri; 	// 페이지번호, 페이지당 보여줄 글의 개수 
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri; 
		this.total = total; 
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10; // 10
		this.startPage = this.endPage - 9; // -9
		int lastPage = (int)(Math.ceil((total * 1.0) / cri.getListQty()));
		if(lastPage < this.endPage) {
			this.endPage = lastPage; 
		}
		
		this.prev = this.startPage > 1; 
		this.next = this.endPage < lastPage; 
		
		
	}
	
}
