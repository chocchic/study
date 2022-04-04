package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public void register(BoardVO board) {
		System.out.println("service register : "+board);
		
		int result = boardMapper.insertSelectKey(board);
		System.out.println("regi fin : " + result);
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		
		return boardMapper.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		// 댓글 삭제후 답글 삭제 replyMapper.deleteReply(bno);
		
		return boardMapper.delete(bno)==1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
//		return boardMapper.getList();
		System.out.println(cri);
		return boardMapper.getListwithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}
	
}
