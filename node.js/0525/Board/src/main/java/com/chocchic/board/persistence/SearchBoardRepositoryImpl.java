package com.chocchic.board.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.chocchic.board.model.Board;
import com.chocchic.board.model.QBoard;
import com.chocchic.board.model.QMember;
import com.chocchic.board.model.QReply;
import com.querydsl.core.Tuple;
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
		// 데이터 하나만 뽑아오는 경우
		QBoard board = QBoard.board;
		
		JPQLQuery<Board> jpqlQuery = from(board);
		// bno가 40인 데이터 조회를 위한 메서드 호출
		jpqlQuery.select(board).where(board.bno.eq(40L));
		
		// 실행
		List<Board> result = jpqlQuery.fetch();
		
		log.info("result : " + result);
		return result.get(0);
		*/
		
		/*
		// 쿼리를 수행할 수 있는 Querydsl객체 중 join하고자 하는 Entity를 가져옵니다.
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		// 관계에서 부모에 해당하는 Entity를 기준으로 쿼리 객체 JPQLQuery를 생성
		JPQLQuery <Board> jpqlQuery = from(board);
		
		// 관계설정에 사용한 속성을 가지고 조인
		// SQL로 보면 : select * from board b, member m where b.email = m.email; 
		jpqlQuery.leftJoin(member).on(board.member.eq(member)); // member와 join
		jpqlQuery.leftJoin(reply).on(reply.board.eq(board)); // reply와 join
		
		// 필요한 데이터를 조회하는 구문을 추가
		// 조인한 데이터를 board별로 묶어서 board와 회원의 email 그리고 댓글의 개수 조회
		jpqlQuery.select(board, member.email, reply.count()).groupBy(board);
		
		// 결과 가져오기
		List<Board> result = jpqlQuery.fetch();
		
		System.out.println(result);
		*/
		
		// 결과를 Tuple로 받기
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		// Tuple은 관계형 데이터베이스에서는 하나의 행을 지칭하는 용어
		// 프로그래밍에서는 일반적으로 여러 종류의 데이터가 묶여서 하나의 데이터를 나타내는 것
		// Map과 다른 점은 Map은 key로 세부 데이터를 접근하지만 Tuple은 인덱스로 접근이 가능하고, 대부분의 경우 Tuple은 수정이 불가능
		JPQLQuery<Board> jpqlquery = from(board);
		jpqlquery.leftJoin(member).on(board.member.eq(member)); // member와 join
		jpqlquery.leftJoin(reply).on(reply.board.eq(board)); // reply와 join
		
		JPQLQuery<Tuple> tuple = jpqlquery.select(board, member.email,reply.count());
		tuple.groupBy(board);
		
		// 결과 가져오기
		List<Tuple> result = tuple.fetch();
		
		System.out.println(result);
		
		return null;
	}

}
