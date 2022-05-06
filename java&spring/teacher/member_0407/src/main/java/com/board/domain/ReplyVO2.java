package com.board.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyVO2 {

	private Long rno; 
	private Long bno; 
	private String reply; 
	private String replyer; 
	private Timestamp replyDate; 
	private Long grp; 
	private Integer step;
	private Integer lev;
	
}
