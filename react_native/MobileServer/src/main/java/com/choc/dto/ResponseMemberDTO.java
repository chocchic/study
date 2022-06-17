package com.choc.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMemberDTO {
	private String error;
	private String email;
	private String password;
	private String name;
	private String imageurl;
	private LocalDateTime lastlogindate;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
}