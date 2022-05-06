package com.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long rno; 
	private Long bno; 
	private String reply; 
	private String replyer; 
	private Timestamp replyDate; 
	private Timestamp updateDate;
	
}
