package com.chocchic.board;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
	private Long bno;
	private String title;
	private String content;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
	
	// 작성자 정보
	private String memberEmail;
	private String memberName;
	
	// 댓굴의 개수
	private int replyCount;
}
