package com.chocchic.board.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chocchic.board.dto.ReplyDTO;
import com.chocchic.board.model.Board;
import com.chocchic.board.model.Reply;
import com.chocchic.board.persistence.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		// 게시글 번호에 해당하느 Reply를 전부 찾아옴
		List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());
		result.sort(new Comparator<Reply>() {
			@Override
			public int compare(Reply o1, Reply o2) {
				// 수정 시간의 내림차순
				// 오름차순의 경우는 o1과 o2의 순서를 바꾸면 됨 -> o1.getModDate().compareTo(o2.getModDate())
				// 숫자의 경우는 뺄셈으 이용하면 됩니다.
				// 양수가 리턴되면 앞의 데이터가 크다고 판단하고 음수가 리턴되면 뒤의 데이터가 크다고 판단해서 음수가 리턴될때 순서를 면경
				// 자바스크립트에서 데이터를 정렬할 때 주의해야합ㄴ디ㅏ.
				// 자바스크립트는 숫자도 문자열로 판단해서 정렬합니다. 숫자 데이터의 경우 정렬하고자 할 때 직접 구현해야합니다.
				return o2.getModDate().compareTo(o1.getModDate());
			}
		});
		return result.stream().map(reply-> entityToDTO(reply)).collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO replyDTO) {
		Reply reply = dtoToEntity(replyDTO);
		replyRepository.save(reply);
	}

	@Override
	public void remove(Long rno) {
		replyRepository.deleteById(rno);		
	}
}