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
	//전송된 파일의 내용을 저장할 속성
	private MultipartFile image; //한번에 업로드 하는 파일이 여러 개라면 MultipartFile [] 
	private LocalDateTime lastlogindate;
	private LocalDateTime regdate;
	private LocalDateTime moddate;
}