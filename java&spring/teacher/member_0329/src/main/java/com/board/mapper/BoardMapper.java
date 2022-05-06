package com.board.mapper;

import java.util.List;

import com.board.domain.BoardVO;

public interface BoardMapper {

	// 글 전체 가져오기 
	public List<BoardVO> getList(); 
	
	// 글 DB에 저장  
	public int insert(BoardVO vo);
	public int insertSelectKey(BoardVO vo);
	
	// 글 하나 가져오기 
	public BoardVO read(Long bno);
	
	// 글 삭제 
	public int delete(Long bno);
	
	// 글 수정 
	public int update(BoardVO vo);
	
	
	
	
	
}
