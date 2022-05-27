package com.chocchic.board.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.chocchic.board.model.Board;
import com.chocchic.board.model.QBoard;
import com.chocchic.board.model.QMember;
import com.chocchic.board.model.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
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

	@Override
	public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
		QBoard board = QBoard.board;
		QReply reply = QReply.reply;
		QMember member = QMember.member;
		
		JPQLQuery<Board> jpqlquery = from(board);
		jpqlquery.leftJoin(member).on(board.member.eq(member)); 
		jpqlquery.leftJoin(reply).on(reply.board.eq(board));
		
		// 검색 결과를 만들어주는 부분
		JPQLQuery<Tuple> tuple = jpqlquery.select(board, member,reply.count());

		// 동적인ㅇ 쿼리 수행을 위한 객체 생성
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		// bno가 0보다 큰 데이터를 추출
		BooleanExpression expression = board.bno.gt(0L);
		
		// type이 검색 항목
		if(type !=null) {
			String[] typeArr = type.split("");
			BooleanBuilder conditionBuilder = new BooleanBuilder();
			for(String t : typeArr) {
				switch(t) {
					case "t":
						conditionBuilder.or(board.title.contains(keyword));
						break;
					case "c":
						conditionBuilder.or(board.content.contains(keyword));
						break;
					case "w":
						conditionBuilder.or(board.member.name.contains(keyword));
						break;
				}
			}
			booleanBuilder.and(conditionBuilder);
		}
		// 조건 적용
		tuple.where(booleanBuilder);
		
		// 데이터 정렬 - 하나의 조건으로만 정렬
		// tuple.orderBy(board.bno.desc());
		// 정렬 조건 가져오기
		Sort sort = pageable.getSort();
		sort.stream().forEach(order ->{
			Order direction = order.isAscending()?Order.ASC:Order.DESC;
			String prop = order.getProperty();
			
			PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
			tuple.orderBy(new OrderSpecifier(direction, orderByExpression.get(prop)));
		});
		
		// 그룹화
		tuple.groupBy(board);
		
		// 페이징 처리
		tuple.offset(pageable.getOffset());
		tuple.limit(pageable.getPageSize());
		
		// 결과를 가져오기
		List<Tuple> result = tuple.fetch();
		
		// 결과를 리턴
		return new PageImpl<Object[]>(result.stream().map(t->t.toArray()).collect(Collectors.toList()), pageable, tuple.fetchCount());
	}

}
