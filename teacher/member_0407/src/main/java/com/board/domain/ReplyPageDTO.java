package com.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

// #2. 댓글 페이징 처리를 위해 : 댓글 목록 + 댓글 전체 개수 담을 DTO 
@Data
@AllArgsConstructor
public class ReplyPageDTO {

	private int replyCount; 	// 댓글 전체 개수 
	private List<ReplyVO2> list; // 댓글 목록 
	
}
