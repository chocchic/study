package com.board.mapper;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

public interface BoardMapper {

	// 글 전체 가져오기 
	public List<BoardVO> getList(); 
	
	// 페이징처리해서 전체 글 가져오기 
	public List<BoardVO> getListWithPaging(Criteria cri); 
	
	// 전체 글의 개수 가져오기 
	public int getTotalCount(Criteria cri);
	
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
