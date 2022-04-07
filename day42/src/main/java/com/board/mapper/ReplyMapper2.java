package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO2;


public interface ReplyMapper2 {

	// CRUD
	// 댓글 등록 
	public int insert(ReplyVO2 vo);
	// 댓글의 답글 등록 
	// step 업데이트 
	public int updateStep(ReplyVO2 vo);
	// 답글 등록 
	public int addReReply(ReplyVO2 vo);
	
	// 댓글 조회 
	public ReplyVO2 read(Long rno); 
	// 댓글 수정 
	public int update(ReplyVO2 vo);
	// 댓글 삭제 
	public int delete(Long rno);
	// 보드content 삭제시 해당 댓글 모두 삭제 
	public int deleteReply(Long bno);
	
	// 댓글 전체 조회 : 본문글번호를 주고 그 본문글에 달린 모든 댓글을 가져오는 메서드 (+paging)
	public List<ReplyVO2> getList(Long bno); 
	
	// 댓글 전체 조회 + 페이징처리  
	public List<ReplyVO2> getListWithPaging(
			@Param("bno") Long bno, 
			@Param("cri") Criteria cri); 
			
	// #1. 댓글 전체 개수 
	public int getRepCount(Long bno);
	
	
	
	
	
	
	
}
