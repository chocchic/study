package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO;
import com.board.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	
	@Override
	public int register(ReplyVO vo) {
		log.info("register vo : " + vo);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("get rno : " + rno);
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify vo : " + vo);
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove rno : " + rno);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, Criteria cri) {
		log.info("get reply list bno : " + bno);
		log.info("get reply list cri : " + cri);
		return replyMapper.getListWithPaging(bno, cri);
	}
	
	
}
