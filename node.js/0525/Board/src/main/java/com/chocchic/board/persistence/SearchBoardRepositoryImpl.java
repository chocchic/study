package com.chocchic.board.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.chocchic.board.model.Board;
import com.chocchic.board.model.QBoard;
import com.chocchic.board.model.QMember;
import com.chocchic.board.model.QReply;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

	public SearchBoardRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public Board search() {
		log.info("search...");
		/*
		QBoard board = QBoard.board;
		
		JPQLQuery<Board> jpqlQuery = from(board);
		// bno가 40인 데이터 조회를 위한 메서드 호출
		jpqlQuery.select(board).where(board.bno.eq(40L));
		
		// 실행
		List<Board> result = jpqlQuery.fetch();
		
		log.info("result : " + result);
		return result.get(0);
		*/
		
		// join하고자 하는 Entity를 가져옵니다.
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		// 관계에서 부모에 해당하는 Entity를 기준으로 JPQLQuery를 생성
		JPQLQuery <Board> jpqlQuery = from(board);
		// 관계설정에 사용한 속성을 가지고 조인
		// SQL로 보면 : select * from board b, member m where b.email = m.email; 
		jpqlQuery.leftJoin(member).on(board.member.eq(member));
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
		
		return null;
	}

}
