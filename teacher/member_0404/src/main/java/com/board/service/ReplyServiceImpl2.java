package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO2;
import com.board.mapper.ReplyMapper2;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl2 implements ReplyService2 {

	@Autowired
	private ReplyMapper2 replyMapper;
	
	
	@Override
	public int register(ReplyVO2 vo) {
		log.info("register vo : " + vo);
		return replyMapper.insert(vo);
	}

	@Override
	public int addReply(ReplyVO2 vo) {
		log.info("addReply vo : " + vo);
		// 기존에 댓글에 달린 답글들이 있다면, 
		// 그 답글들의 step 을 하나씩 올려주고, 
		replyMapper.updateStep(vo); 
		// 방금 받은 데이터 추가 
		vo.setStep(vo.getStep()+1);
		vo.setLev(vo.getLev()+1);
		log.info("addReply vo : " + vo);
		int result = replyMapper.addReReply(vo);
		
		return result;
	}
	
	@Override
	public ReplyVO2 get(Long rno) {
		log.info("get rno : " + rno);
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO2 vo) {
		log.info("modify vo : " + vo);
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove rno : " + rno);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO2> getList(Long bno, Criteria cri) {
		log.info("get reply list bno : " + bno);
		log.info("get reply list cri : " + cri);
		return replyMapper.getListWithPaging(bno, cri);
	}


	
	
}
