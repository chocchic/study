package com.chocchic.board.service;

import java.util.List;

import com.chocchic.board.dto.ReplyDTO;
import com.chocchic.board.model.Board;
import com.chocchic.board.model.Reply;

public interface ReplyService {

	// 댓글 등록
	public Long register(ReplyDTO replyDTO);
	
	// 게시글 번호를 이용해서 댓글의 목록을 가져오는 메서드
	public List<ReplyDTO> getList(Long bno);
	
	// 댓글로 수정하는 메서드
	public void modify(ReplyDTO replyDTO);
	
	// 댓글을 삭제하는 메서드
	public void remove(Long rno);
	
	// ReplyDTO 객체를 Reply 객체로 변환하는 메서드
	default Reply dtoToEntity(ReplyDTO replyDTO) {
		Board board = Board.builder().bno(replyDTO.getBno()).build();
		Reply reply = Reply.builder().rno(replyDTO.getRno()).text(replyDTO.getText()).replyer(replyDTO.getReplyer()).board(board).build();
		return reply;
	}
	
	// Reply Entity를 ReplyDTO 객체로 변환하는 메서드
	default ReplyDTO entityToDTO(Reply reply) {
		ReplyDTO dto = ReplyDTO.builder().rno(reply.getRno()).text(reply.getText()).regdate(reply.getRegDate()).moddate(reply.getModDate()).build();
		return dto;
	}
}
