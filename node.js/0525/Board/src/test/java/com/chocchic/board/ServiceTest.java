package com.chocchic.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chocchic.board.dto.PageRequestDTO;
import com.chocchic.board.dto.PageResultDTO;
import com.chocchic.board.service.BoardService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private BoardService b;
	
	//@Test
	public void testRegister() {
		BoardDTO dto = BoardDTO.builder().title("test").content("test content").memberEmail("choc1@aa.com").build();
		Long bno = b.register(dto);
		System.out.println("test reg : "+bno);
	}
	
	//@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = new PageRequestDTO();
		PageResultDTO<BoardDTO, Object[]> result = b.getList(pageRequestDTO);
		
		for(BoardDTO boardDTO : result.getDtoList()) {
			System.out.println(boardDTO);
		}
	}
	
	//@Test
	public void testBoard() {
		BoardDTO dto = b.getBoard(40L);
		System.out.println("test board : " + dto);
	}
	
	//@Test
	public void testDelete() {
		b.removeWithReplies(2L);
	}
	
	@Test
	public void testModifyBoard() {
		BoardDTO boardDTO = BoardDTO.builder().bno(3L).title("제목을 수정").content("내용을 수정").build();
		b.modifyBoard(boardDTO);
	}
}
