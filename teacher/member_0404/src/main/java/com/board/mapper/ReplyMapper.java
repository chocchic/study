package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO;

public interface ReplyMapper {

	// CRUD
	// 댓글 등록 
	public int insert(ReplyVO vo);
	// 댓글 조회 
	public ReplyVO read(Long rno); 
	// 댓글 수정 
	public int update(ReplyVO vo);
	// 댓글 삭제 
	public int delete(Long rno);
	
	// 댓글 전체 조회 : 본문글번호를 주고 그 본문글에 달린 모든 댓글을 가져오는 메서드 (+paging)
	public List<ReplyVO> getList(Long bno); 
	
	// 댓글 전체 조회 + 페이징처리  
	public List<ReplyVO> getListWithPaging(
			@Param("bno") Long bno, 
			@Param("cri") Criteria cri); 
			
	
	
	
	
	
	
	
	
}
