package com.choc.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private String email;
	private String password;
	private String name;
	private String imageurl;
	// 전송된 파일의 내용을 저장할 속성
	private MultipartFile image;
	private LocalDateTime lastlogindate;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
}
