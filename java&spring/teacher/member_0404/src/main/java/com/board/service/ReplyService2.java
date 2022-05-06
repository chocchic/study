package com.board.service;

import java.util.List;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO2;

public interface ReplyService2 {

	// 댓글 등록 
	public int register(ReplyVO2 vo); 
	// 댓글의 답글 등록 
	public int addReply(ReplyVO2 vo);
	
	// 댓글 한개 조회 
	public ReplyVO2 get(Long rno); 
	// 댓글 수정 
	public int modify(ReplyVO2 vo); 
	// 댓글 삭제 
	public int remove(Long rno); 
	// 댓글 전체 조회 
	public List<ReplyVO2> getList(Long bno, Criteria cri);
	
	
	
}
