package com.chocchic.board.service;

import org.springframework.stereotype.Service;

import com.chocchic.board.BoardDTO;
import com.chocchic.board.model.Board;
import com.chocchic.board.persistence.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{
	private final BoardRepository boardRepostiory;
	@Override
	public Long register(BoardDTO dto) {
		log.info(dto);
		Board board = dtoToEntity(dto);
		boardRepostiory.save(board);
		return board.getBno();
	}
}
