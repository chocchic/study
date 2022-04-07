package com.board.service;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

public interface BoardService {

	// 글 저장 
	public void register(BoardVO board); 
	// 글 하나 가져오기 
	public BoardVO get(Long bno); 
	// 글 수정 
	public boolean modify(BoardVO board); 
	// 글 삭제 
	public boolean remove(Long bno); 
	// 전체 글 가져오기 
	//public List<BoardVO> getList(); 
	// 전체글 페이징처리해서 가져오기 
	public List<BoardVO> getList(Criteria cri);
	// 전체 글의 개수 가져오기 
	public int getTotal(Criteria cri); 
	
}
