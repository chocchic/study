package com.chocchic.board.service;

import java.util.Optional;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chocchic.board.BoardDTO;
import com.chocchic.board.dto.PageRequestDTO;
import com.chocchic.board.dto.PageResultDTO;
import com.chocchic.board.model.Board;
import com.chocchic.board.model.Member;
import com.chocchic.board.persistence.BoardRepository;
import com.chocchic.board.persistence.ReplyRepository;

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
	
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);
		
		// Entity를 DTO로 변환해주는 함수 생성
		// Repository의 메서드의 결과가 Object[]인데 이 배열의 요소를 가지고 BoardDTO를 생성해서 출력해야 함
		Function<Object[], BoardDTO> fn = (en -> entitytoDTO((Board)en[0], (Member)en[1], (Long)en[2]));
		
		// 데이터를 조회 - bno의 내림차순 적용
		// 상황에 따라서는 regdate나 moddate로 정렬하는 경우도 있음
		/*
		Page<Object[]> result = boardRepostiory.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
		*/
		Page<Object[]> result = boardRepostiory.searchPage(pageRequestDTO.getType(),pageRequestDTO.getKeyword(), pageRequestDTO.getPageable(Sort.by("bno").descending()));
		return new PageResultDTO<>(result, fn);
	}

	@Override
	public BoardDTO getBoard(Long bno) {
		// bno를 이용해서 하나의 데이터 가져오기
		// Board, Member, Long - 댓글 개수
		Object result = boardRepostiory.getBoardByBno(bno);
		Object[] ar = (Object[]) result;
		
		return entitytoDTO((Board)ar[0], (Member)ar[1], (Long)ar[2]);
	}

	private final ReplyRepository replyRepo;
	
	// 이 메서드 안의 작업은 하나의 트랜젝션으로 처리해달라고 요청
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {
		// 댓글 삭제
		replyRepo.deleteByBno(bno);
		// 게시글 삭제
		boardRepostiory.deleteById(bno);
	}
	
	@Transactional
	@Override
	public void modifyBoard(BoardDTO boardDTO) {
		// 데이터의 존재 여부를 확인
		Optional<Board> board = boardRepostiory.findById(boardDTO.getBno());
		if(board.isPresent()) {
			board.get().changeTitle(boardDTO.getTitle());
			board.get().changeContent(boardDTO.getContent());
			
			boardRepostiory.save(board.get());
		}
	}
}
