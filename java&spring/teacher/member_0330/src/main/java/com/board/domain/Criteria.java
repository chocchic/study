package com.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria { // 페이징처리시 부수적으로 필요한 정보들 담아줄 객체 

	private int pageNum; 	// 페이지 번호 
	private int listQty;	// 한페이지에 보여줄 게시물 개수 
	
	public Criteria() {
		this(1, 10);
	}
	public Criteria(int pageNum, int listQty) {
		this.pageNum = pageNum; 
		this.listQty = listQty;
	}
	
	// 파라미터 완성해서 돌려주는 메서드 
	public String getParameterLink() {
		// ?pageNum=3&listQty=10
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("listQty", this.listQty); 
		System.out.println("uri string : " + builder.toUriString());
		
		return builder.toUriString();
	}
	
	
	
	
}
