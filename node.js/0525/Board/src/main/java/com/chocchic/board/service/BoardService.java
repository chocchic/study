package com.chocchic.board.service;

import com.chocchic.board.BoardDTO;
import com.chocchic.board.model.Board;
import com.chocchic.board.model.Member;

public interface BoardService {
	// DTO를 Entity로 변환하는 메서드
	default Board dtoToEntity(BoardDTO dto) {
		Member member = Member.builder().email(dto.getMemberEmail()).build();
		Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent()).member(member).build();
		
		return board;
	}
	
	// Entity를 DTO로 변환해주는 메서드
	default BoardDTO entitytoDTO(Board board, Member member, Long replyCount) {
		BoardDTO boardDTO = BoardDTO.builder().bno(board.getBno()).title(board.getTitle()).content(board.getContent())
				.regdate(board.getRegDate()).moddate(board.getModDate()).memberEmail(member.getEmail())
				.memberName(member.getName()).replyCount(replyCount.intValue()).build();
		return boardDTO;		
	}
}
