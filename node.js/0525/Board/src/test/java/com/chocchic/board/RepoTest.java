package com.chocchic.board;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.chocchic.board.model.Board;
import com.chocchic.board.model.Member;
import com.chocchic.board.model.Reply;
import com.chocchic.board.persistence.BoardRepository;
import com.chocchic.board.persistence.MemberRepository;
import com.chocchic.board.persistence.ReplyRepository;

@SpringBootTest
public class RepoTest {
	@Autowired
	private MemberRepository m;
	
	//@Test
	public void insertMember() {
		IntStream.rangeClosed(0, 99).forEach(i -> {
			Member member = Member.builder().email("choc"+i+"@aa.com").password("1234").name("촉촉한초코칩"+i).build();
			m.save(member);
		});
	}
	
	@Autowired
	private BoardRepository b;
	
	//@Test
	public void insertBoards() {
		IntStream.rangeClosed(0,99).forEach(i ->{
			Member member = Member.builder().email("choc0@aa.com").build(); // 없는 이메일 입력시 오류
			Board board = Board.builder().title("제목..."+i).content("내용..."+i).member(member).build();
			b.save(board);
		});
	}
	
	@Autowired
	private ReplyRepository r;
	
	//@Test
	public void insertReplys() {
		IntStream.rangeClosed(0,99).forEach(i ->{
			// 0부터 99사이의 정수를 랜덤하게 생성해서 Board객체를 생성
			long bno = (long)(Math.random()*100)+2;
			Board board = Board.builder().bno(bno).build();
			Reply reply = Reply.builder().content("댓글..."+i).board(board).replyer("촉촉한초코칩"+i).build();
			r.save(reply);
		});
	}
	
	// 하나의 board데이터를 조회하는 메서드 : bno 2부터 시작
	//@Test
	//@Transactional
	public void readBoard() {
		Optional<Board> result = b.findById(50L);
		// 데이터를 출력
		System.out.println(result.get());
		System.out.println(result.get().getMember());
	}
	
	// 하나의 reply데이터를 조회하는 메서드
	//@Test
	public void readReply() {
		Optional<Reply> result = r.findById(850L);
		// 데이터를 출력
		System.out.println(result.get());
		System.out.println(result.get().getBoard());
	}
	
	//@Test
	public void testReadWithWriter() {
		// 데이터 조회
		Object result = b.getBoardWithMember(40L);
		// JPQL의 결과가 Object인 경우는 Object[]로 강제 형변환해서 사용
		System.out.println((Object[])result);
	}
	
	
	//@Test
	public void testGetBoardWithReply() {
		List<Object[]> result = b.getBoardWithReply(40L);
		for(Object[] ar : result) {
			System.out.println(Arrays.toString(ar));
		}
	}
	
	//@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = b.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row ->{
			Object[] ar = (Object[])row;
			System.out.println(Arrays.toString(ar));
		});
	}
	
	//@Test
	public void testByBno() {
		Object result = b.getBoardByBno(40L);
		Object[] ar = (Object[])result;
		System.out.println(Arrays.toString(ar));
	}
	
	//@Test
	public void testSearch() {
		b.search();
	}
	
	@Test
	public void testSearchPage() {
		Pageable p = PageRequest.of(0,10,Sort.by("bno").descending().and(Sort.by("title").ascending()));
		Page<Object[]> result = b.searchPage("tcw", "9", p);
		System.out.println("result : " + result);
	}
}